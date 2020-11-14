package spm.project.restaurantrecommendation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class LoginController {



    @GetMapping("/home")
    public String getIndexPage() {
        return "index-test";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login-test";
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration-test";
    }
}
