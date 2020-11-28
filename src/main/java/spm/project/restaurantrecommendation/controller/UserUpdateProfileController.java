package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import spm.project.restaurantrecommendation.dto.UserUpdateInfoDto;
import spm.project.restaurantrecommendation.entity.User;
import spm.project.restaurantrecommendation.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// :::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu ::::::::::
// :::::::::::::::::::::::::::::::::::::::::


@Controller
public class UserUpdateProfileController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserUpdateInfoDto userUpdateInfoDto(Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        return userService.updateUserInfo(user);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/user-profile")
    public String showProfilePage(Principal principal, Model model, @ModelAttribute("user1") User user,
                                  Authentication authentication) {
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<String> roles = new ArrayList<String>();
            for (GrantedAuthority a : authorities) {
                roles.add(a.getAuthority());
            }
            if (isUser(roles)) {
                user = userService.findByUsername(principal.getName());
                model.addAttribute("user_firstName", user.getFirstName());
                model.addAttribute("user_lastName", user.getLastName());
                model.addAttribute("user_email", user.getEmail());
                model.addAttribute("user_phone", user.getPhone());
            }
        }
        System.out.println("Username: " + principal.getName());
        System.out.println("Authentication: " + authentication.getPrincipal());
        System.out.println("User ID: " + user.getId());
        return "user-profile";
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/user-update-profile")
    public String showUpdatePage() {return "user-update-profile";}


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/user/user-update-profile")
    public String updateUserInfo(Authentication authentication, @ModelAttribute("user") @Valid UserUpdateInfoDto userUpdateInfoDto,
                                 BindingResult result, Principal principal) {
        if (authentication == null) return "redirect:/user";
        if (result.hasErrors()) return "user-update-profile";

        userService.save(userUpdateInfoDto);
        return "redirect:/user/user-update-profile?success";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/change-password")
    public String showChangePasswordPage() {
        return "change-password";
    }

    private boolean isUser(List<String> roles) {
        if (roles.contains("USER")) {
            return true;
        }
        return false;
    }

}
