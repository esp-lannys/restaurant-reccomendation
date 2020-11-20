package spm.project.restaurantrecommendation.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Controller
public class LoginController {


    @PreAuthorize("!(hasRole('USER') OR hasRole('ADMIN'))")
    @GetMapping({ "/" })
    public String getIndexPage() {
        return "index-test";
    }


    @GetMapping("/user")
    public String userPage() {
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "redirect:/admin/index";
    }

    @PreAuthorize("!(hasRole('USER') OR hasRole('ADMIN'))")
    @GetMapping("/login")
    public String login(){ return "login"; }

    @GetMapping("/resetpassword")
    public String getResetPasswordPage() { return "resetpass"; }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/index")
    public String getAdminPage() {
        return "admin/index";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/fragments/listRestaurants")
    public String getListRestaurants() {
        return "admin/fragments/listRestaurants";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/fragments/listAccounts")
    public String getlistAccounts() {
        return "admin/fragments/listAccounts";
    }

    @PreAuthorize("!(hasRole('USER') OR hasRole('ADMIN'))")
    @GetMapping("/403")
    public String get403Page() {  return "403"; }

}
