package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import spm.project.restaurantrecommendation.dto.LocationDto;
import spm.project.restaurantrecommendation.entity.Location;
import spm.project.restaurantrecommendation.service.LocationService;

import java.security.Principal;
import java.util.List;

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
    @GetMapping({"/"})
    public String root(Principal principal, Authentication auth, Model model
            , @ModelAttribute("location") LocationDto locationDto){
        List<Location> locationList = locationService.findAll();
        model.addAttribute("locations",locationList);
        return "index";
    }

    ////////////////////////////
    //////////////////////////// 403 PAGE
    ////////////////////////////

    @PreAuthorize("!(hasRole('USER') OR hasRole('ADMIN'))")
    @GetMapping("/403")
    public String get403Page() {  return "403"; }


}
