package spm.project.restaurantrecommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spm.project.restaurantrecommendation.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating,Long> {
}
