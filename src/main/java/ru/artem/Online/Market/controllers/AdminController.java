package ru.artem.Online.Market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.artem.Online.Market.models.Car;
import ru.artem.Online.Market.models.User;
import ru.artem.Online.Market.services.GarageService;

/**
 * Контроллер для команд администратора
 */
@Controller
public class AdminController {
    @Autowired
    private GarageService garageService;

    @Autowired
    private User user;

    /**
     * Метод собирает информацию для представления adminAddCar
     *
     * @param model модель
     * @return представление adminAddCar
     */
    @GetMapping("/adminAddCar")
    public String addCarPage(Model model) {
        model.addAttribute("car", new Car());
        return "adminAddCar";
    }

    /**
     * Метод добавляет машину в гараж
     *
     * @param newCar информация о машине
     * @param model  модель
     * @return представление menu
     */
    @PostMapping("/adminAddCarProgress")
    public String addCar(@ModelAttribute("car") Car newCar, Model model) {
        garageService.addCar(newCar);
        model.addAttribute("user", user);
        return "menu";
    }

    /**
     * Метод собирает информацию для представления adminRemoveCar
     *
     * @param model модель
     * @return представление adminRemoveCar
     */
    @GetMapping("/adminRemoveCar")
    public String removeCarPage(Model model) {
        model.addAttribute("car", new Car());
        return "adminRemoveCar";
    }

    /**
     * Метод удаляет машину из гаража и возвращает в меню
     *
     * @param newCar информация о машине
     * @param model  модель
     * @return перенаправляет в URL /menu
     */
    @PostMapping("/adminRemoveCarSuccess")
    public String removeCar(@ModelAttribute("car") Car newCar, Model model) {
        garageService.removeCar(newCar);
        return "redirect:/menu";
    }

}
