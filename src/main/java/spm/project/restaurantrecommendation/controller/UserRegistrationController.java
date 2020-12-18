package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import spm.project.restaurantrecommendation.dto.UserDto;
import spm.project.restaurantrecommendation.entity.Mail;
import spm.project.restaurantrecommendation.entity.User;
import spm.project.restaurantrecommendation.service.EmailService;
import spm.project.restaurantrecommendation.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@PreAuthorize("!(hasRole('USER') OR hasRole('ADMIN'))")
@Controller
public class UserRegistrationController {

    @Autowired
    private EmailService emailService;

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
            , BindingResult result, HttpServletRequest request) throws Exception{
        User existingUsername = userService.findByUsername(userDto.getUsername());
        User existingEmail = userService.findByEmail(userDto.getEmail());
        if (existingEmail != null || existingUsername != null) {
            result.rejectValue("email", null, "There is email or username already an account registered");
        }
        if (result.hasErrors()){
            return "registration";
        }
        userService.save(userDto);

        User savedUser = userService.findByEmail(userDto.getEmail());

        if (savedUser == null) {
            result.rejectValue("email","We could not find an account for that e-mail address");
            return "registration";
        }

        Mail mail = new Mail();
        mail.setFrom("practice.project.noreply@gmail.com");
        mail.setTo(savedUser.getEmail());
        mail.setSubject("Welcome to GOURMETTE");

        Map<String, Object> mailModel = new HashMap<>();
        mailModel.put("user", savedUser);
        mailModel.put("signature","THE FIFTH ORANGE ORGANIZATION");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        mailModel.put("registrationUrl",url + "/registration?=" + savedUser.getUsername());
        mail.setModel(mailModel);
        emailService.sendEmail(mail);
        return "redirect:/registration?success";
    }
}
