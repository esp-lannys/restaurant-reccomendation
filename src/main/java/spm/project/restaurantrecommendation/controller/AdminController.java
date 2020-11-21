package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import spm.project.restaurantrecommendation.entity.Role;
import spm.project.restaurantrecommendation.entity.User;
import spm.project.restaurantrecommendation.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// :::::::::::::::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu + @nhatquang::::::::::
// :::::::::::::::::::::::::::::::::::::::::::::::::::::

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String showAdminIndex(){
        return "admin/index";
    }

    @ModelAttribute("userList")
    private List<User> getUserList(Principal principal){
        if (principal == null){
            return null;
        }
        String username = principal.getName();
        User user = userService.findByUsername(username);
        List<User> oldList = userService.findAll();
        //List<User> newList = oldList.stream().filter(userRole -> userRole.getRoles().equals("USER")).collect(Collectors.toList());
        return oldList;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/fragments/listRestaurants")
    public String getListRestaurants() {
        return "admin/fragments/listRestaurants";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/fragments/listAccounts")
    public String getlistAccounts(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/fragments/listAccounts";
    }
}
