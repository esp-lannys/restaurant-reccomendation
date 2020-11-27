package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import spm.project.restaurantrecommendation.entity.Role;
import spm.project.restaurantrecommendation.entity.User;
import spm.project.restaurantrecommendation.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
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

    @GetMapping("/admin")
    public String showAdminIndex(Model model){
        model.addAttribute("users", userService.findAll());
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

    //@GetMapping("/fragments/listRestaurants")
    @GetMapping("/admin/listRestaurants")
    public String getListRestaurants() {
        return "admin/fragments/listRestaurants";
    }

    //@GetMapping("/fragments/listAccounts")
    @GetMapping("/admin/listAccounts")
    public String getlistAccounts(Model model, Principal principal) {
        if (principal == null) return "redirect:/";
        model.addAttribute("users", userService.findAll());
        return "admin/fragments/listAccounts";
    }

    @GetMapping("/admin/search-user")
    public String searchUser(@RequestParam String kw, Principal principal){
        if (principal == null) {
            return "redirect:/";
        }
        List<User> listUser = getUserList(principal);
        List<User> list = new ArrayList<User>();

        for (User a : listUser) {
            if (is(a.getId().toString(),kw) || is(a.getUsername(),kw)
                    || is(a.getFirstName(), kw) || is(a.getLastName(), kw) || is(a.getPhone(), kw)
                    || is(a.getEmail(),kw))
                list.add(a);
        }
        return "admin/fragments/listAccounts";
    }

    @GetMapping("/admin/delete-user-{id}")
    public String deleteUser(@PathVariable long id, Principal principal, HttpServletRequest request, HttpServletResponse response){
        if (principal == null) return null;

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
}
