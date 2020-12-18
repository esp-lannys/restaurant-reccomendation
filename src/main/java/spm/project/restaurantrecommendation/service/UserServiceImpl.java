package spm.project.restaurantrecommendation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spm.project.restaurantrecommendation.dto.UserChangePasswordDto;
import spm.project.restaurantrecommendation.dto.UserDto;
import spm.project.restaurantrecommendation.dto.UserUpdateInfoDto;
import spm.project.restaurantrecommendation.entity.PasswordResetToken;
import spm.project.restaurantrecommendation.entity.Restaurant;
import spm.project.restaurantrecommendation.entity.Role;
import spm.project.restaurantrecommendation.entity.User;
import spm.project.restaurantrecommendation.repository.PasswordResetTokenRepository;
import spm.project.restaurantrecommendation.repository.RoleRepository;
import spm.project.restaurantrecommendation.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }


    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    @Override
    public void autoLogin(String username) {
        UserDetails userDetails = loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    @Override
    public UserUpdateInfoDto updateUserInfo(User user) {
        UserUpdateInfoDto uuid = new UserUpdateInfoDto();
        uuid.setId(user.getId());
        uuid.setEmail(user.getEmail());
        uuid.setFirstName(user.getFirstName());
        uuid.setLastName(user.getLastName());
        uuid.setPhone(user.getPhone());
        return uuid;
    }

        @Override
        public User save(UserUpdateInfoDto userUpdateInfoDto) {
        User user = userRepository.findById((long) userUpdateInfoDto.getId()).get();
        user.setEmail(userUpdateInfoDto.getEmail());
        user.setPhone(userUpdateInfoDto.getPhone());
        user.setFirstName(userUpdateInfoDto.getFirstName());
        user.setLastName(userUpdateInfoDto.getLastName());
        user.setUpdatedAt(userUpdateInfoDto.getUpdate_date());
        user.setUuid(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public UserChangePasswordDto updateUserPassword(User user) {
        UserChangePasswordDto ucp = new UserChangePasswordDto();
        ucp.setId(user.getId());
        ucp.setEmail(user.getEmail());
        ucp.setFirstName(user.getFirstName());
        ucp.setLastName(user.getLastName());
        ucp.setUsername(user.getUsername());
        ucp.setPassword(user.getPassword());
        ucp.setConfirmPassword(user.getPassword());
        return ucp;
    }

    @Override
    public void updatePassword(String password, Long id) {
        userRepository.updatePassword(password, id);
    }

    @Override
    public List<User> adminSearchAcc(String keyword) {
        return userRepository.adminSearchAcc(keyword);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                grantedAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> grantedAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public User save(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhone(userDto.getPhone());
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRoleName("USER"));
        user.setRoles(roles);
        return userRepository.save(user);
    }
}
