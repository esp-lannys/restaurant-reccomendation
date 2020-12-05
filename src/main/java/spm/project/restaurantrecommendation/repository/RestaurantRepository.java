package spm.project.restaurantrecommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import spm.project.restaurantrecommendation.entity.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r from Category c inner join c.restaurants r where c.id = :idCategory")
    List<Restaurant> findRestaurantByCategoryId(@RequestParam("idCategory") Long id);
}
