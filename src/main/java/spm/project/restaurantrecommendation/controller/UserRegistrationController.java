package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spm.project.restaurantrecommendation.dto.UserDto;
import spm.project.restaurantrecommendation.entity.User;
import spm.project.restaurantrecommendation.service.UserService;
import javax.validation.Valid;

// :::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu ::::::::::
// :::::::::::::::::::::::::::::::::::::::::

@PreAuthorize("!(hasRole('USER') OR hasRole('ADMIN'))")
@Controller
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserDto userRegistrationDto() {
        return new UserDto();
    }

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto
            , BindingResult result, Model model) throws Exception{
//        System.out.println(userDto.getUsername() + "head");
//        User existingUsername = userService.findByUsername(userDto.getUsername());
//        System.out.println(existingUsername);
//        User existingEmail = userService.findByEmail(userDto.getEmail());
//        System.out.println(existingEmail);
//        System.out.println(userDto.getUsername() + "after checking");
//        if (existingEmail != null || existingUsername != null) {
//            result.rejectValue("email", null, "There is email or username already an account registered");
//        }
//        System.out.println(userDto.getUsername() + "mid");
        if (result.hasErrors()){
            return "registration";
        }
        userService.save(userDto);
        //System.out.println(userDto.getUsername() + "tail");
        return "redirect:/registration?success";
    }
}
