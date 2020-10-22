package spm.project.restaurantrecommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spm.project.restaurantrecommendation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
