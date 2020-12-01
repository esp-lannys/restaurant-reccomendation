package spm.project.restaurantrecommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Modifying
    @Query("DELETE FROM User where id = :id")
    void deleteById(@Param("id") Long id);

    @Modifying
    @Query("update User u set u.password = :password where u.id = :id")
    void updatePassword(@Param("password") String password, @Param("id") Long id);
}
