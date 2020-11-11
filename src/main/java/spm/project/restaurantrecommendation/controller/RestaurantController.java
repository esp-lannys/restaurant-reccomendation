package spm.project.restaurantrecommendation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import spm.project.restaurantrecommendation.entity.Restaurant;
import spm.project.restaurantrecommendation.repository.RestaurantRepository;
import spm.project.restaurantrecommendation.exception.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class RestaurantController {
    @Autowired
    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurant(){return restaurantRepository.findAll();}

    @GetMapping("/restaurants/{id}")
    public Restaurant getRestaurantByID(@PathVariable long id){
        return restaurantRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id));
    }

    @PostMapping("/restaurants")
    public Restaurant addRestaurant(@RequestBody Restaurant res){
        return restaurantRepository.save(res);
    }

    @DeleteMapping("/restaurants/{id}")
    public void deleteRestaurant(@PathVariable long id){
        restaurantRepository.deleteById(id);
    }
}
