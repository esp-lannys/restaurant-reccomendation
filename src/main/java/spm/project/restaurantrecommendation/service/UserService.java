package spm.project.restaurantrecommendation.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import spm.project.restaurantrecommendation.entity.User;


public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    User findByUsername(String username);

}
