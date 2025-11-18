package ru.artem.Online.Market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.artem.Online.Market.models.Car;
import ru.artem.Online.Market.models.User;
import ru.artem.Online.Market.services.GarageService;
import ru.artem.Online.Market.services.UserService;

import java.util.List;

/**
 * Главный контроллер
 */
@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private GarageService garageService;

    /**
     * Метод собирает информацию для представления menu
     *
     * @param model модель
     * @return представление menu
     */
    @GetMapping("/menu")
    public String getMenu(Model model) {
        model.addAttribute("user", userService.getUser());
        return "menu";
    }

    /**
     * Метод собирает информацию для представления profile
     *
     * @param model модель
     * @return представление profile
     */
    @GetMapping("/profile")
    public String getProfile(Model model) {
        model.addAttribute("user", userService.getUser());
        return "profile";
    }

    /**
     * Метод собирает информацию для представления garage
     *
     * @param model модель
     * @return представление garage
     */
    @GetMapping("/garage")
    public String getGarage(Model model) {
        model.addAttribute("garage", garageService.getGarage());
        return "garage";
    }

    /**
     * Метод собирает информацию для представления rented
     *
     * @param model модель
     * @return представление rented
     */
    @GetMapping("/rented")
    public String getRentedCars(Model model) {
        model.addAttribute("user", userService.getUser());
        return "rented";
    }

    /**
     * Метод собирает информацию для представления changeProfilePage
     *
     * @param model модель
     * @return представление changeProfilePage
     */
    @GetMapping("/changeProfilePage")
    public String getChangeProfilePage(Model model) {
        model.addAttribute("user", userService.getUser());
        return "changeProfilePage";
    }

    /**
     * Метод редактирует профиль пользователя
     *
     * @param newUser информация о пользователе
     * @param model   модель
     * @return перенаправляет в URL /profile
     */
    @PostMapping("/changeProfile")
    public String changeProfile(@ModelAttribute("user") User newUser, Model model) {
        model.addAttribute("newUser", newUser);
        userService.setUser(userService.fillUser(newUser));
        return "redirect:/profile";
    }

    /**
     * Метод собирает информацию для представления carRentPage
     *
     * @param model модель
     * @return представление carRentPage
     */
    @GetMapping("/carRentPage")
    public String getCarRentPage(Model model) {
        model.addAttribute("car", new Car());
        return "carRentPage";
    }

    /**
     * Метод открывает представление rentSucceed
     *
     * @return представление rentSucceed
     */
    @GetMapping("/rentSucceed")
    public String getRentSucceed() {
        return "rentSucceed";
    }

    /**
     * Метод собирает информацию для представления error
     *
     * @param message текст ошибки
     * @param model   модель
     * @return представление error
     */
    @GetMapping("/error/{message}")
    public String throwError(@PathVariable("message") String message, Model model) {
        model.addAttribute("message", message);
        return "error";
    }

    /**
     * Метод осуществляет аренду машины
     *
     * @param newCar информация о машине
     * @param time   срок аренды
     * @param model  модель
     * @return перенаправляет в URL /rentSucceed
     */
    @PostMapping("/carRent")
    public String getCarRent(@ModelAttribute("car") Car newCar,
                             @RequestParam("time") int time,
                             Model model) {
        model.addAttribute("car", newCar);
        if (garageService.findCar(newCar) && !userService.getUser().getRented().contains(newCar)) {
            Car rentedCar = garageService.findCarValue(newCar);
            List<Car> goodCars = garageService.getFoundCars(newCar);

            model.addAttribute("goodCars", goodCars);
            userService.rent(rentedCar, time);
            return "redirect:/rentSucceed";
        } else {
            String message = "An issue occurred. This car is not in stock.";
            return "redirect:/error/" + message;
        }
    }
}
