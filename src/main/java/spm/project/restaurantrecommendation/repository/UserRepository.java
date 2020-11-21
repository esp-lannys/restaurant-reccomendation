package spm.project.restaurantrecommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spm.project.restaurantrecommendation.entity.User;

import java.util.Optional;

// :::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu ::::::::::
// :::::::::::::::::::::::::::::::::::::::::

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    //@Query("select u from User u where u.email = ?1")
    User findByEmail(String email);

    User findByUsername(String username);

}
