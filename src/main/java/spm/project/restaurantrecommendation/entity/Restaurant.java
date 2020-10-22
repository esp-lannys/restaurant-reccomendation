package spm.project.restaurantrecommendation.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Long longitude;
    private Long latitude;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY,mappedBy = "restaurant")
    private Set<TableForReservation> tableForReservations;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY,mappedBy = "restaurant")
    private Set<Comments> comments;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Rating rating;
}
