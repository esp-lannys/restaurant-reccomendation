package spm.project.restaurantrecommendation.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
public class LoginController {


    @PreAuthorize("!(hasRole('USER') OR hasRole('ADMIN'))")
    @GetMapping({ "/" })
    public String getIndexPage(Principal principal, Authentication authentication) {
        return "index-test";
    }
    @PreAuthorize("!(hasRole('USER') OR hasRole('ADMIN'))")
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
    @PreAuthorize("!(hasRole('USER') OR hasRole('ADMIN'))")
    @GetMapping("/403")
    public String get403Page() {  return "403"; }

}
