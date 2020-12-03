package spm.project.restaurantrecommendation.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import spm.project.restaurantrecommendation.dto.UserChangePasswordDto;
import spm.project.restaurantrecommendation.dto.UserDto;
import spm.project.restaurantrecommendation.dto.UserUpdateInfoDto;
import spm.project.restaurantrecommendation.entity.PasswordResetToken;
import spm.project.restaurantrecommendation.entity.User;

import java.util.List;
import java.util.Optional;

// :::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu ::::::::::
// :::::::::::::::::::::::::::::::::::::::::

public interface UserService extends UserDetailsService {
    User findByEmail(String email);

    User findByUsername(String username);

//    Optional<User> findById(Long id);

    User findById(Long id);

    User save(UserDto userDto);

    void deleteById(Long id);

    List<User> findAll();

    PasswordResetToken findByToken(String token);

    void autoLogin(String username);

    UserUpdateInfoDto updateUserInfo(User user);

    User save (UserUpdateInfoDto userUpdateInfoDto);

    UserChangePasswordDto updateUserPassword(User user);

    void updatePassword(String password, Long id);
}
