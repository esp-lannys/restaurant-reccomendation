package spm.project.restaurantrecommendation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spm.project.restaurantrecommendation.entity.User;
import spm.project.restaurantrecommendation.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() { return userRepository.findAll(); }
}
