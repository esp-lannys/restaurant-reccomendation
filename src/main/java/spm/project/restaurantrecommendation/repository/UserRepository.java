package spm.project.restaurantrecommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spm.project.restaurantrecommendation.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
