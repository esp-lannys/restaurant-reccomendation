package spm.project.restaurantrecommendation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import spm.project.restaurantrecommendation.entity.Role;
import spm.project.restaurantrecommendation.entity.User;
import spm.project.restaurantrecommendation.repository.RoleRepository;
import spm.project.restaurantrecommendation.repository.UserRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;

@Component
public class DataService implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (roleRepository.findByRoleName("USER") == null){
            roleRepository.save(new Role("USER"));
        }
        if (roleRepository.findByRoleName("ADMIN") == null){
            roleRepository.save(new Role("ADMIN"));
        }
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setId((long) 1);
            admin.setFirstName("ADMIN");
            admin.setLastName("");
            admin.setPhone("0886282283");
            admin.setUsername("admin");
            admin.setEmail("nphoangtu@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            Date date = new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            admin.setCreatedAt(ts);
            admin.setUpdatedAt(ts);
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByRoleName("ADMIN"));
            admin.setRoles(roles);
            userRepository.save(admin);
        }
    }
}
