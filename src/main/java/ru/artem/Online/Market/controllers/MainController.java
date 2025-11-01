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
     * Метод создает страницу для меню
     *
     * @param model модель
     * @return страница
     */
    @GetMapping("/menu")
    public String getMenu(Model model) {
        model.addAttribute("user", userService.getUser());
        return "menu";
    }

    /**
     * Метод создает страницу для профиля
     *
     * @param model модель
     * @return страница
     */
    @GetMapping("/profile")
    public String getProfile(Model model) {
        model.addAttribute("user", userService.getUser());
        return "profile";
    }

    /**
     * Метод создает страницу для гаража
     *
     * @param model модель
     * @return страница
     */
    @GetMapping("/garage")
    public String getGarage(Model model) {
        model.addAttribute("garage", garageService.getGarage());
        return "garage";
    }

    /**
     * Метод создает страницу для арендованных пользователем машин
     *
     * @param model модель
     * @return страница
     */
    @GetMapping("/rented")
    public String getRentedCars(Model model) {
        //userService.getUser().getRented().forEach(x-> System.out.println(x.getBrand()+" "+x.getModel()));
        model.addAttribute("user", userService.getUser());
        return "rented";
    }

    /**
     * Метод создает страницу для редактирования профиля
     *
     * @param model модель
     * @return страница
     */
    @GetMapping("/changeProfilePage")
    public String getChangeProfilePage(Model model) {
        model.addAttribute("user", userService.getUser());
        return "changeProfilePage";
    }

    /**
     * Метод редактирует профиль
     *
     * @param model модель
     * @return страница
     */
    @PostMapping("/changeProfile")
    public String changeProfile(@ModelAttribute("user") User newUser, Model model) {
        model.addAttribute("newUser", newUser);
        userService.setUser(userService.fillUser(newUser));
        return "redirect:/profile";
    }

    /**
     * Метод создает страницу для аренды машины
     *
     * @param model модель
     * @return страница
     */
    @GetMapping("/carRentPage")
    public String getCarRentPage(Model model) {
        model.addAttribute("car", new Car());
        return "carRentPage";
    }

    /**
     * Метод создает страницу для уведомления пользователя об успешной сделке
     *
     * @return страница
     */
    @GetMapping("/rentSucceed")
    public String getRentSucceed() {
        return "rentSucceed";
    }

    /**
     * Метод создает страницу для ошибки
     *
     * @param model модель
     * @return страница
     */
    @GetMapping("/error/{message}")
    public String throwError(@PathVariable("message") String message, Model model) {
        model.addAttribute("message", message);
        return "error";
    }

    /**
     * Метод берет в аренду машину
     *
     * @param model модель
     * @return страница
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
