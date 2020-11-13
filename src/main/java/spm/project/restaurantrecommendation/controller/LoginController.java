package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spm.project.restaurantrecommendation.service.UserService;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
