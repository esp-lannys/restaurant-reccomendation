package spm.project.restaurantrecommendation.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// :::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu ::::::::::
// :::::::::::::::::::::::::::::::::::::::::

@Controller
public class LoginController {    

    @PreAuthorize("!(hasRole('USER') OR hasRole('ADMIN'))")
    @GetMapping("/login")
    public String login () {
            return "login";
        }

    @GetMapping("/resetpassword")
    public String getResetPasswordPage () {
            return "resetpass";
        }


}
