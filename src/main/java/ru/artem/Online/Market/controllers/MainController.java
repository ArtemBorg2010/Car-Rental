package ru.artem.Online.Market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.artem.Online.Market.models.Car;
import ru.artem.Online.Market.models.Garage;
import ru.artem.Online.Market.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private Car car;

    @Autowired
    private Garage garage;

    @Autowired
    private User user;

    @Autowired
    private Car rentedCar;

    @GetMapping("/menu")
    public String getMenu(Model model) {
        //model.addAllAttributes(Map.of());
        model.addAttribute("car", car);
        garage.fill();
        return "menu";
    }

    @GetMapping("/profile")
    public String getProfile(Model model){
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
        if (garage.findCar(newCar)) {
            Car rentCar=garage.findCarValue(newCar);
            user.rent(rentCar);
            List<Car> goodCars = garage.getFoundCars(newCar);
            model.addAttribute("goodCars", goodCars);
            return "foundCar";
        } else {
            return "redirect:/error";
        }
    }
    @GetMapping("/changeProfilePage")
    public String getChangeProfilePage(Model model){
        model.addAttribute("user",user);
        return "changeProfilePage";
    }

    @PostMapping("/changeProfile")
    public String changeProfile(@ModelAttribute("user") User newUser, Model model){
        model.addAttribute("newUser",newUser);
        return "redirect:/changeProfilePage";
    }

    @GetMapping("/carRentPage")
    public String getCarRentPage(Model model) {
        model.addAttribute("car", car);
        return "carRentPage";
    }

    @PostMapping("/carRent")
    public String getCarRent(@ModelAttribute("car") Car newCar, Model model) {
        model.addAttribute("car", newCar);
        rentedCar=newCar;
        return "redirect:/foundCar";
    }

    @GetMapping("/rentSuccess")
    public String getRentSuccess(){
        System.out.println();
        user.rent(rentedCar);
        return "menu";
    }
}
