package spm.project.restaurantrecommendation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserUpdateProfileController {

    @GetMapping("/user-profile")
    public String showProfilePage() {
        return "user-profile";
    }

    @GetMapping("/user-update-profile")
    public String showUpdatePage() {return "user-update-profile";}
}
