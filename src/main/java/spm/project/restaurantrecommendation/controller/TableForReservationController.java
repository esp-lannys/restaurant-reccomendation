package spm.project.restaurantrecommendation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import spm.project.restaurantrecommendation.entity.TableForReservation;
import spm.project.restaurantrecommendation.repository.TableForReservationRepository;
import spm.project.restaurantrecommendation.exception.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class TableForReservationController {
    @Autowired
    private final TableForReservationRepository tableForReservationRepository;

    public TableForReservationController(TableForReservationRepository tableForReservationRepository){
        this.tableForReservationRepository = tableForReservationRepository;
    }

    @GetMapping("/tfr")
    public List<TableForReservation> getAllTFR(){return tableForReservationRepository.findAll();}

    @GetMapping("/tfr/{id}")
    public TableForReservation getTFRByID(@PathVariable long id){
        return tableForReservationRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id));
    }

    @PostMapping("/tfr")
    public TableForReservation addTFR(@RequestBody TableForReservation tfr){
        return tableForReservationRepository.save(tfr);
    }

    @DeleteMapping("/tfr/{id}")
    public void deleteTFR(@PathVariable long id){tableForReservationRepository.deleteById(id);}
}
