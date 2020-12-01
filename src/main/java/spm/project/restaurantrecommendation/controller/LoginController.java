package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spm.project.restaurantrecommendation.entity.User;
import spm.project.restaurantrecommendation.service.UserService;
import java.util.List;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// :::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu ::::::::::
// :::::::::::::::::::::::::::::::::::::::::

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

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
