package spm.project.restaurantrecommendation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import spm.project.restaurantrecommendation.entity.Reservation;
import spm.project.restaurantrecommendation.repository.ReservationRepository;
import spm.project.restaurantrecommendation.exception.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/reservation")
    public List<Reservation> getAllReservation(){return reservationRepository.findAll();}

    @GetMapping("/reservation/{id}")
    public Reservation getReserByID(@PathVariable long id){
        return reservationRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id));
    }

    @PostMapping("/reservation")
    public Reservation addReservation(@RequestBody Reservation reservation){
        return reservationRepository.save(reservation);
    }

    @DeleteMapping("/reservation/{id}")
    public void deleteReservation(@PathVariable long id){
        reservationRepository.deleteById(id);
    }
}
