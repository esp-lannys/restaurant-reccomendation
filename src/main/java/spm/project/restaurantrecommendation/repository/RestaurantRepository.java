package spm.project.restaurantrecommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spm.project.restaurantrecommendation.entity.Restaurant;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
