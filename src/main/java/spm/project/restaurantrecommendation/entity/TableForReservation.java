package spm.project.restaurantrecommendation.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TableForReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String additionalRequest;
    private int numOfSeats;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Reservation reservation;
}
