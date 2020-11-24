package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spm.project.restaurantrecommendation.entity.Location;
import spm.project.restaurantrecommendation.service.LocationService;
import spm.project.restaurantrecommendation.service.UserService;

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
    public String userPage(Model model) {
        List<Location> locationList = locationService.findAll();
        model.addAttribute("locations",locationList);
        return "redirect:/";
    }


}
