package spm.project.restaurantrecommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spm.project.restaurantrecommendation.entity.TableForReservation;

public interface TableForReservationRepository extends JpaRepository<TableForReservation, Long> { }
