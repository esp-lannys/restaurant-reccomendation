package spm.project.restaurantrecommendation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roleName;


    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE} , mappedBy = "roles")
    private Set<User> user;
}
