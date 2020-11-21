package spm.project.restaurantrecommendation.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @PreAuthorize("!(hasRole('USER') OR hasRole('ADMIN'))")
    @GetMapping({"/"})
    public String root(Principal principal, Authentication auth){
        return "index";
    }
}