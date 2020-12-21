package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import spm.project.restaurantrecommendation.dto.UserChangePasswordDto;
import spm.project.restaurantrecommendation.dto.UserUpdateInfoDto;
import spm.project.restaurantrecommendation.entity.User;
import spm.project.restaurantrecommendation.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class UserUpdateProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute("user")
    public UserUpdateInfoDto userUpdateInfoDto(Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        return userService.updateUserInfo(user);
    }

    @ModelAttribute("user2")
    public UserChangePasswordDto userChangePassword(Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        return userService.updateUserPassword(user);
    }

    // User Profile Page

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

    // User Update Profile Page

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/user-update-profile")
    public String showUpdatePage(Principal principal, Authentication authentication) {
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<String> roles = new ArrayList<String>();
            for (GrantedAuthority a : authorities) {
                roles.add(a.getAuthority());
            }
            if (!isUser(roles)) {
                return "/403";
            }
        }
        return "user-update-profile";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/user/user-update-profile")
    public String updateUserInfo(Authentication authentication,
            @ModelAttribute("user") @Valid UserUpdateInfoDto userUpdateInfoDto, BindingResult result,
            Principal principal) {
        if (authentication == null)
            return "redirect:/user";
        if (result.hasErrors())
            return "user-update-profile";

        userService.save(userUpdateInfoDto);
        return "redirect:/user/user-update-profile?success";
    }

    // User Reservation History

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/reservation-history")
    public String showReservationHistoryPage() {
        return "reservation-history";
    }

    // User Change Password Page

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/change-password")
    public String showChangePasswordPage() {
        return "change-password";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/user/change-password")
    public String changeUserPassword(Authentication authentication,
            @ModelAttribute("user2") @Valid UserChangePasswordDto userChangePasswordDto, BindingResult result,
            Principal principal, Model model) {

        if (authentication == null)
            return "redirect:/user";

        if (result.hasErrors())
            return "change-password";

        User user = userService.findByUsername(principal.getName());

        String updated_password = passwordEncoder.encode(userChangePasswordDto.getPassword());
        userService.updatePassword(updated_password, user.getId());
        userService.loadUserByUsername(userChangePasswordDto.getUsername());
        System.out.println(userChangePasswordDto.getUsername());
        return "redirect:/user/change-password?success";
    }

    private boolean isUser(List<String> roles) {
        if (roles.contains("USER")) {
            return true;
        }
        return false;
    }

}
