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

@Controller
public class AdminController {
    @Autowired
    Garage garage;

    @Autowired
    Car car;

    @Autowired
    User user;

    Car changedCar;

    @GetMapping("/adminAddCar")
    public String addCarPage(Model model){
        model.addAttribute("car",car);
        return "adminAddCar";
    }

    @PostMapping("/adminAddCarProgress")
    public String addCar(@ModelAttribute("car") Car newCar, Model model){
        changedCar=newCar;
        garage.addCar(newCar);
        model.addAttribute("user",user);
        return "menu";
    }

//    @GetMapping("/adminCarAdded")
//    public String carAdded(){
//        car= changedCar;
//        return "menu";
//    }
}
