package spm.project.restaurantrecommendation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class LoginController {



    @GetMapping({ "/" })
    public String getIndexPage() {
        return "index-test";
    }

    @GetMapping("/login")
    public String login(){ return "login"; }

    @GetMapping("/login-test")
    public String getLoginPage() {
        return "login-test";
    }

    @GetMapping("/resetpassword")
    public String getResetPasswordPage() { return "resetpass"; }

    @GetMapping("/registration-test")
    public String getRegistrationTestPage() {
        return "registration-test";
    }

    @GetMapping("/admin/index")
    public String getAdminPage(Model model) {
        model.addAttribute("listRestaurants","listRestaurants");
        return "admin/index";
    }

    @GetMapping("/403")
    public String get403Page() {  return "403"; }

}
