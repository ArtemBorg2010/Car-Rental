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

@Controller
public class AdminController {
    @Autowired
    private GarageService garageService;

    @Autowired
    private User user;

    @GetMapping("/adminAddCar")
    public String addCarPage(Model model) {
        model.addAttribute("car", new Car());
        return "adminAddCar";
    }

    @PostMapping("/adminAddCarProgress")
    public String addCar(@ModelAttribute("car") Car newCar, Model model) {
        garageService.addCar(newCar);
        model.addAttribute("user", user);
        return "menu";
    }

    @GetMapping("/adminRemoveCar")
    public String removeCarPage(Model model) {
        model.addAttribute("car", new Car());
        return "adminRemoveCar";
    }

    @PostMapping("/adminRemoveCarProgress")
    public String removeCar(@ModelAttribute("car") Car newCar, Model model) {
        garageService.removeCar(newCar);
        return "redirect:/menu";
    }

}
