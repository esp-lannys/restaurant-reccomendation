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
import java.util.UUID;

// :::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu ::::::::::
// :::::::::::::::::::::::::::::::::::::::::

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
            admin.setId((long) 6);
            admin.setFirstName("ADMIN");
            admin.setLastName("SYSTEM");
            admin.setPhone("000000000");
            admin.setUsername("admin");
            admin.setEmail("nphoangtu@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setUuid(UUID.randomUUID().toString());
            Date date1 = new Date();
            long time = date1.getTime();
            Timestamp ts = new Timestamp(time);
            admin.setCreatedAt(ts);
            admin.setUpdatedAt(ts);
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByRoleName("ADMIN"));
            admin.setRoles(roles);
            userRepository.save(admin);
        }

        if (userRepository.findByUsername("user") == null) {
            User user = new User();
            user.setId((long) 7);
            user.setFirstName("USER");
            user.setLastName("TEST");
            user.setPhone("0099924612");
            user.setUsername("user");
            user.setEmail("user@user.com");
            user.setPassword(passwordEncoder.encode("user"));
            user.setUuid(UUID.randomUUID().toString());
            Date date = new Date();
            long time1 = date.getTime();
            Timestamp ts = new Timestamp(time1);
            user.setCreatedAt(ts);
            user.setUpdatedAt(ts);
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByRoleName("USER"));
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}
