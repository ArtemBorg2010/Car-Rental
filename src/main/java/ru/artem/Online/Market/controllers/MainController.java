package ru.artem.Online.Market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.artem.Online.Market.models.Car;
import ru.artem.Online.Market.models.Garage;
import ru.artem.Online.Market.models.User;
import ru.artem.Online.Market.services.GarageService;
import ru.artem.Online.Market.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private GarageService garageService;

    @Autowired
    private Car car;

    @Autowired
    private Garage garage;

    @Autowired
    private User user;

    @Autowired
    private Car rentedCar;

    int days;

    @GetMapping("/menu")
    public String getMenu(Model model) {
        model.addAttribute("user", user);
        return "menu";
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/garage")
    public String getGarage(Model model) {
        model.addAttribute("garage", garage);
        model.addAttribute("car", car);
        return "garage";
    }

    @GetMapping("/rented")
    public String getRentedCars(Model model) {
        model.addAttribute("user", user);
        return "rented";
    }

    @GetMapping("/errorNoCarFound")
    public String throwError() {
        return "errorNoCarFound";
    }

    @PostMapping("/foundCar")
    public String getFoundCar(@ModelAttribute("car") Car newCar, Model model) {
        model.addAttribute("car", newCar);
        if (garageService.findCar(newCar)) {
            rentedCar = garageService.findCarValue(newCar);
            List<Car> goodCars = garageService.getFoundCars(newCar);
            model.addAttribute("goodCars", goodCars);
            return "foundCar";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/changeProfilePage")
    public String getChangeProfilePage(Model model) {
        model.addAttribute("user", user);
        return "changeProfilePage";
    }

    @PostMapping("/changeProfile")
    public String changeProfile(@ModelAttribute("user") User newUser, Model model) {
        model.addAttribute("newUser", newUser);
        user = userService.fillUser(newUser);
        return "redirect:/profile";
    }

    @GetMapping("/carRentPage")
    public String getCarRentPage(Model model) {
        model.addAttribute("car", car);
        return "carRentPage";
    }

    @PostMapping("/carRent")
    public String getCarRent(@ModelAttribute("car") Car newCar,
                             @RequestParam("time") int time,
                             Model model) {
        model.addAttribute("car", newCar);
        days = time;
        return "redirect:/foundCar";
    }

    @GetMapping("/rentSuccess")
    public String getRentSuccess() {
        userService.rent(rentedCar, days);
        return "redirect:/menu";
    }

    @GetMapping("/addFundsPage")
    public String addFundsPage(Model model) {
        model.addAttribute("user", user);
        return "addFundsPage";
    }
}
