package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import spm.project.restaurantrecommendation.repository.TableForReservationRepository;

import java.util.List;

@RestController
public class TableForReservationController {
    @Autowired
    private final TableForReservationRepository tableForReservationRepository;

    public TableForReservationController(TableForReservationRepository tableForReservationRepository) {
        this.tableForReservationRepository = tableForReservationRepository;
    }
}