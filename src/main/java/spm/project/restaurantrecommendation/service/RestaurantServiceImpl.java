package spm.project.restaurantrecommendation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spm.project.restaurantrecommendation.entity.Restaurant;
import spm.project.restaurantrecommendation.repository.RestaurantRepository;

import java.util.List;

@Service("restaurantService")
@Transactional
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> findAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id).get();
    }

    @Override
    public List<Restaurant> findRestaurantByCategoryId(Long id) {
        return restaurantRepository.findRestaurantByCategoryId(id);
    }
}
