package spm.project.restaurantrecommendation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Table;
@Entity
@Data
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Role> roles;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Rating> ratings;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Reservation> reservations;
}
