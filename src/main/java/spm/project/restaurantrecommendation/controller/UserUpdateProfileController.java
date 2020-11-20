package spm.project.restaurantrecommendation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserUpdateProfileController {

    @GetMapping("/user-profile")
    public String showUpdatePage() {
        return "user-profile";
    }


}
