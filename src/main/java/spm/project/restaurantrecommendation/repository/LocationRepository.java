package spm.project.restaurantrecommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spm.project.restaurantrecommendation.entity.Location;
import spm.project.restaurantrecommendation.entity.Restaurant;

import java.util.Collection;
import java.util.List;

// :::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu ::::::::::
// :::::::::::::::::::::::::::::::::::::::::

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByDistrict(String district);

    @Query("select l from Restaurant r inner join r.locations l where l.id = :id")
    List<Location> findLocationByRestaurantId(@Param("id") Long id);

    @Query("select l from Location l inner join l.restaurants r where r in (:restaurant)")
    List<Location> findLocationByRestaurants(Restaurant restaurant);
}
