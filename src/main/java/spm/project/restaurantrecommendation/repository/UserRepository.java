package spm.project.restaurantrecommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import spm.project.restaurantrecommendation.entity.Restaurant;
import spm.project.restaurantrecommendation.entity.User;

import java.util.List;
import java.util.Optional;

// :::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu ::::::::::
// :::::::::::::::::::::::::::::::::::::::::

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    //@Query("select u from User u where u.email = ?1")
    User findByEmail(String email);

    User findByUsername(String username);

    //@Query("SELECT u FROM User u WHERE u.id = :id")
    //Optional<User> findById(@Param("id") Long id);
    @Query("SELECT u FROM User u WHERE u.username LIKE %:keyword%")
    public List<User> adminSearchAcc(@RequestParam("keyword") String keyword);


    @Modifying
    @Query("DELETE FROM User where id = :id")
    void deleteById(@Param("id") Long id);

    @Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    void updatePassword(@Param("password") String password, @Param("id") Long id);
}
