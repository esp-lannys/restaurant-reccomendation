package spm.project.restaurantrecommendation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class LoginController {



    @GetMapping("/home")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "login-test";
    }
}
