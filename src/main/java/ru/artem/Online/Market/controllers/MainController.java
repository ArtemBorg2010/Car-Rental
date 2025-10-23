package ru.artem.Online.Market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.artem.Online.Market.models.Car;
import ru.artem.Online.Market.models.User;
import ru.artem.Online.Market.services.GarageService;
import ru.artem.Online.Market.services.UserService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private GarageService garageService;

    @GetMapping("/menu")
    public String getMenu(Model model) {
        model.addAttribute("user", userService.getUser());
        return "menu";
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        model.addAttribute("user", userService.getUser());
        return "profile";
    }

    @GetMapping("/garage")
    public String getGarage(Model model) {
        model.addAttribute("garage", garageService.getGarage());
        return "garage";
    }

    @GetMapping("/rented")
    public String getRentedCars(Model model) {
        model.addAttribute("user", userService.getUser());
        return "rented";
    }

    @GetMapping("/errorNoCarFound")
    public String throwError() {
        return "errorNoCarFound";
    }

    @PostMapping("/foundCar")
    public String getFoundCar(@ModelAttribute("car") Car newCar,
                              @RequestParam("time") int time,
                              Model model) {
        model.addAttribute("car", newCar);
        if (!garageService.rentACar(newCar,time).isEmpty()){
            model.addAttribute("goodCars", garageService.rentACar(newCar,time));
            return "redirect:/menu";
        }else{
            return "redirect:/errorNoCarFound";
        }
    }

    @GetMapping("/changeProfilePage")
    public String getChangeProfilePage(Model model) {
        model.addAttribute("user", userService.getUser());
        return "changeProfilePage";
    }

    @PostMapping("/changeProfile")
    public String changeProfile(@ModelAttribute("user") User newUser, Model model) {
        model.addAttribute("newUser", newUser);
        userService.setUser(userService.fillUser(newUser));
        return "redirect:/profile";
    }

    @GetMapping("/carRentPage")
    public String getCarRentPage(Model model) {
        model.addAttribute("car", new Car());
        return "carRentPage";
    }

    @PostMapping("/carRent")
    public String getCarRent(@ModelAttribute("car") Car newCar,
                             @RequestParam("time") int time,
                             Model model) {
        model.addAttribute("car", newCar);
        if (garageService.findCar(newCar)) {
            Car rentedCar = garageService.findCarValue(newCar);
            List<Car> goodCars = garageService.getFoundCars(newCar);
            model.addAttribute("goodCars", goodCars);
            userService.rent(rentedCar,time);
            return "menu";
        } else {
            return "redirect:/error";
        }
    }
}
