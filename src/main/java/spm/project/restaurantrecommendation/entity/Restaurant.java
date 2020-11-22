package spm.project.restaurantrecommendation.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

// :::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu ::::::::::
// :::::::::::::::::::::::::::::::::::::::::

@Entity
@Getter
@Setter
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String img;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY,mappedBy = "restaurant")
    private Set<TableForReservation> tableForReservations;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY,mappedBy = "restaurant")
    private Set<Comments> comments;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST},fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Rating rating;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "restaurant_location",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private Set<Location> locations;
}
