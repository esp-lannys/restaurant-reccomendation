package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spm.project.restaurantrecommendation.dto.UserUpdateInfoDto;
import spm.project.restaurantrecommendation.entity.Restaurant;
import spm.project.restaurantrecommendation.entity.Role;
import spm.project.restaurantrecommendation.entity.User;
import spm.project.restaurantrecommendation.service.RestaurantService;
import spm.project.restaurantrecommendation.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// :::::::::::::::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu + @nhatquang::::::::::
// :::::::::::::::::::::::::::::::::::::::::::::::::::::

@PreAuthorize("hasRole('ADMIN')")
@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/admin")
    public String showAdminIndex(Principal principal, Authentication authentication){

        if (principal == null) return "/403";

        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<String> roles = new ArrayList<String>();
            for (GrantedAuthority a : authorities) {
                roles.add(a.getAuthority());
            }
            if (!isAdmin(roles)) {
                return "redirect:/403";
            }
        }
        return "admin/index";
    }

    // Admin update user page

    @GetMapping("/admin/edit-user/{id}")
    public String showAdminUpdate(Model model,
                                  @PathVariable long id,
                                  Principal principal,
                                  HttpServletRequest request,
                                  HttpServletResponse response, Authentication authentication){
        if (principal == null) {
            return "redirect:/admin";
        }

        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<String> roles = new ArrayList<String>();
            for (GrantedAuthority a : authorities) {
                roles.add(a.getAuthority());
            }
            if (!isAdmin(roles)) {
                return "/403";
            }
        }

        User u = userService.findById(id);

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }

        model.addAttribute("user", u);

        return "admin/update";
    }

    // Admin update user

    @PostMapping("/admin/edit-user/{id}")
    public String updateUserInfo(Authentication authentication,
                                 @PathVariable long id,
                                 @ModelAttribute("user") @Valid UserUpdateInfoDto userUpdateInfoDto,
                                 BindingResult result,
                                 Principal principal) {

        if (authentication == null) return "redirect:/admin";

        if (result.hasErrors()) return "redirect:/admin";

        userService.save(userUpdateInfoDto);
        userService.autoLogin(principal.getName());
        return "redirect:/admin";

    }


    // generate user list (optional)

    @ModelAttribute("userList")
    private List<User> getUserList(Principal principal){
        if (principal == null) {
            return null;
        }

        String username = principal.getName();
        User user = userService.findByUsername(username);
        List<User> oldList = userService.findAll();
        //List<User> newList = oldList.stream().filter(userRole -> userRole.getRoles().equals("USER")).collect(Collectors.toList());
        return oldList;
    }

    //@GetMapping("/fragments/listRestaurants")
    @GetMapping("/admin/listRestaurants")
    public String getListRestaurants(Model model, Principal principal) {
        if (principal == null) return "redirect:/403";
        model.addAttribute("restaurants", restaurantService.findAllRestaurants());
        return "admin/fragments/listRestaurants";
    }


    //@GetMapping("/fragments/listAccounts")
    @GetMapping("/admin/listAccounts")
    public String getlistAccounts(Model model, Principal principal) {
        if (principal == null) return "redirect:/403";
        model.addAttribute("users", userService.findAll());
        return "admin/fragments/listAccounts";
    }

    // Admin search user

    @GetMapping("/admin/search-user")
    public String searchUser(@RequestParam("keyword") String kw, Principal principal, Model model, HttpServletRequest request){
        if (principal == null) {
            return "redirect:/";
        }

        if (kw.equals("")) return "redirect:/admin/listAccounts";

        List<User> listUser = getUserList(principal);
        List<User> list = new ArrayList<User>();

        for (User a : listUser) {
            if (is(a.getId().toString(),kw) || is(a.getUsername(),kw)
                    || is(a.getFirstName(), kw) || is(a.getLastName(), kw) || is(a.getPhone(), kw)
                    || is(a.getEmail(),kw))
                list.add(a);
        }


        request.getSession().setAttribute("users", list);
        model.addAttribute("users", list);

        return "admin/fragments/listAccounts";
    }

    // Admin delete user

    @GetMapping("/admin/delete-user-{id}")
    public String deleteUser(@PathVariable long id, Principal principal, HttpServletRequest request, HttpServletResponse response){
        if (principal == null) {
            return "redirect:/admin";
        }

        User u = userService.findByUsername(principal.getName());
        if (id == u.getId()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
        }

        userService.deleteById(id);
        return "redirect:/admin/listAccounts";
    }

    // Admin search restaurant

    @GetMapping("/admin/search-restaurant")
    public String searchRestaurant(@RequestParam("keyword") String kw, Principal principal
                                    , Model model, HttpServletRequest request) {
        if (principal == null) {
            return "redirect:/";
        }

        if (kw.equals("")) {
            return "redirect:/admin/listAccounts";
        }

        List<Restaurant> restaurantList = restaurantService.findAllRestaurants();
        List<Restaurant> list = new ArrayList<Restaurant>();

        for (Restaurant restaurant : restaurantList) {
            if (is(restaurant.getName(),kw) || is(restaurant.getId().toString(),kw) || is(restaurant.getAddress(),kw)
                || is(restaurant.getImg(),kw) || is(restaurant.getPhone(),kw) ) {
                list.add(restaurant);
            }
        }

        request.getSession().setAttribute("restaurants", list);
        model.addAttribute("restaurants", list);

        return "admin/fragments/listRestaurants";
    }

    boolean is(String a, String b) {
        a = unAccent(a);
        b = unAccent(b);
        b.replace("+", " ");
        b.replace("%20", " ");
        b = b.toLowerCase();
        a = a.toLowerCase();
        return b.equalsIgnoreCase(a);
    }

    boolean error(String a, String b) {
        a = unAccent(a);
        b = unAccent(b);
        System.out.println(a);
        System.out.println(b);
        b.replace("%20", "+");
        b = b.toLowerCase();
        a = a.toLowerCase();
        String[] arr = b.split("\\+");
        System.out.println(a);
        System.out.println(arr.toString());
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

    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ADMIN")) {
            return true;
        }
        return false;
    }
}
