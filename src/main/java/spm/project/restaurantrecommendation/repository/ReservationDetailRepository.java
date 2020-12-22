package spm.project.restaurantrecommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spm.project.restaurantrecommendation.entity.ReservationDetail;

@Repository
public interface ReservationDetailRepository extends JpaRepository<ReservationDetail,Long> {
    @Query("select r from ReservationDetail  r where r.email = ?1")
    ReservationDetail findByEmail(String email);
}
