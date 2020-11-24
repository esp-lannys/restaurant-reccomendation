package spm.project.restaurantrecommendation.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import spm.project.restaurantrecommendation.dto.UserDto;
import spm.project.restaurantrecommendation.entity.PasswordResetToken;
import spm.project.restaurantrecommendation.entity.User;

import java.util.List;

// :::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu ::::::::::
// :::::::::::::::::::::::::::::::::::::::::

public interface UserService extends UserDetailsService {
    User findByEmail(String email);

    User findByUsername(String username);

    User save(UserDto userDto);

    void deleteById(Long id);

    List<User> findAll();

    PasswordResetToken findByToken(String token);
}
