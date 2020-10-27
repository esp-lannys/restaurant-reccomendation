package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import spm.project.restaurantrecommendation.repository.ReservationRepository;

import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
}