package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spm.project.restaurantrecommendation.entity.Location;
import spm.project.restaurantrecommendation.entity.Restaurant;
import spm.project.restaurantrecommendation.service.LocationService;
import spm.project.restaurantrecommendation.service.RestaurantService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;


@PreAuthorize("!(hasRole('USER') OR hasRole('ADMIN'))")
@Controller
public class RestaurantController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurant")
    public String showRestaurantDetailPage(Model model, ModelMap map, Authentication authentication, Principal principal) {
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<String> roles = new ArrayList<String>();
            for (GrantedAuthority a : authorities) {
                roles.add(a.getAuthority());
            }
            if (isUser(roles)) {
                map.addAttribute("navbar", "navbar-authenticated");
            } else {
                map.addAttribute("navbar", "navbar");
            }
        }

        return "restaurant";
    }

    @GetMapping("/restaurant/{id}")
    public String showRestaurantDetail(@PathVariable("id") Long id,
                                       Model model, ModelMap map, Authentication authentication, Principal principal) {
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<String> roles = new ArrayList<String>();
            for (GrantedAuthority a : authorities) {
                roles.add(a.getAuthority());
            }
            if (isUser(roles)) {
                map.addAttribute("navbar", "navbar-authenticated");
            } else {
                map.addAttribute("navbar", "navbar");
            }
        }
        Restaurant restaurant = restaurantService.findById(id);
        model.addAttribute("restaurant", restaurant);

        return "restaurant";
    }

    private boolean isUser(List<String> roles) {
        if (roles.contains("USER")) {
            return true;
        }
        return false;
    }
}
