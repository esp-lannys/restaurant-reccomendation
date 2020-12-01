package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import spm.project.restaurantrecommendation.entity.Location;
import spm.project.restaurantrecommendation.service.LocationService;
import spm.project.restaurantrecommendation.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// :::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu ::::::::::
// :::::::::::::::::::::::::::::::::::::::::

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;

    @GetMapping("/user")
    public String userPage(Authentication authentication, ModelMap map) {
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<String> roles = new ArrayList<String>();
            for (GrantedAuthority a : authorities) {
                roles.add(a.getAuthority());
            }
            if (isUser(roles)) {
                map.addAttribute("navbar", "navbar-authenticated");
            }
        }
        return "redirect:/";
    }

    @GetMapping("/searchRestaurant")
    public String searchRestaurants(Principal principal, Authentication authentication){

        return null;
    }

    private boolean isUser(List<String> roles) {
        if (roles.contains("USER")) {
            return true;
        }
        return false;
    }

    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ADMIN")) {
            return true;
        }
        return false;
    }
}
