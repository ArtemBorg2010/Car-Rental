package ru.artem.Online.Market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.artem.Online.Market.models.User;

import java.util.Objects;

@Controller
public class AuthorisationController {
    @Autowired
    private User user;

    @GetMapping("/authorisationLabel")
    public String greetingLabel(Model model) {
        model.addAttribute("user", user);
        return "authorisationLabel";
    }

    @PostMapping("/authorisation")
    public String greet(@ModelAttribute("user") User user) {
        if (Objects.equals(user.getUsername(), "Artem") &&
                Objects.equals(user.getPassword(), "1")) {
            user.setAdmin(true);
        }
        return "redirect:/menu";
    }
}
