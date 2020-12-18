package spm.project.restaurantrecommendation.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;


    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<User> user;

    public Role(){}

    public Role(String roleName){
        this.roleName = roleName;
    }
}
