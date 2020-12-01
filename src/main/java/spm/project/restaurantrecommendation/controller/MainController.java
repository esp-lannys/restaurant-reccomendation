package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import spm.project.restaurantrecommendation.service.LocationService;
import java.security.Principal;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

// :::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu ::::::::::
// :::::::::::::::::::::::::::::::::::::::::

@Controller
public class MainController {

    @Autowired
    private LocationService locationService;

    ////////////////////////////
    //////////////////////////// index PAGE
    ////////////////////////////

    @PreAuthorize("!(hasRole('USER') OR hasRole('ADMIN'))")
    @GetMapping({ "/" })
    public String root(Principal principal, Authentication authentication, ModelMap map) {
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
        return "index";
    }

    @GetMapping({ "/contact" })
    public String showContactPage() {
        return "contact";
    }

    @GetMapping({ "/about" })
    public String showAboutPage() {
        return "about";
    }

    @GetMapping({ "/reservation" })
    public String showReservationPage() {
        return "reservation";
    }

    @GetMapping({ "/restaurant" })
    public String getRestaurant() {
        return "restaurant";
    }

    ////////////////////////////
    //////////////////////////// 403 PAGE
    ////////////////////////////

    @PreAuthorize("!(hasRole('USER') OR hasRole('ADMIN'))")
    @GetMapping("/403")
    public String get403Page() {
        return "403";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
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

    // For search engine
    boolean is(String a, String b) {
        a = unAccent(a);
        b = unAccent(b);
        b.replace("+", " ");
        b.replace("%20", " ");
        b = b.toLowerCase();
        a = a.toLowerCase();
        return b.equalsIgnoreCase(a);
    }

    // Handling error
    boolean error(String a, String b) {
        a = unAccent(a);
        b = unAccent(b);
        b.replace("%20", "+");
        b = b.toLowerCase();
        a = a.toLowerCase();
        String[] arr = b.split("\\+");
        for (String item : arr)
            if (a.contains(item))
                return true;
        return false;
    }

    public static String unAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replace("đ", "d");
    }
}
