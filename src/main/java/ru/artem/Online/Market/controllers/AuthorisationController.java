package ru.artem.Online.Market.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.artem.Online.Market.models.User;
import ru.artem.Online.Market.services.UserService;

import java.util.Objects;

@Controller
public class AuthorisationController {
    @Autowired
    private User user;

    @Autowired
    private UserService userService;

    @GetMapping("/authorisationLabel")
    public String greetingLabel(Model model) {
        model.addAttribute("user", user);
        return "authorisationLabel";
    }

    @PostMapping("/authorisation")
    public String greet(@ModelAttribute("user") User user2) {
        if (Objects.equals(user2.getUsername(), "Artem") &&
                Objects.equals(user2.getPassword(), "123")) {
            user.setAdmin(true);
        }
        user.setUsername(user2.getUsername());
        user.setPassword(user2.getPassword());

        userService.setUser(user);

        return "redirect:/menu";
    }
}
