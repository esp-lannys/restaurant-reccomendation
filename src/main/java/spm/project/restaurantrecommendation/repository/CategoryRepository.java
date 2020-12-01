package spm.project.restaurantrecommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spm.project.restaurantrecommendation.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByCategoryName(String name);

    @Query("select c from Restaurant r inner join r.categories c where r.id = :idRestaurant")
    List<Category> findCategoryByIdRestaurant(@Param("idRestaurant") Long id);
}
