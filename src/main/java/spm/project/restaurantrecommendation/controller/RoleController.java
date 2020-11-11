package spm.project.restaurantrecommendation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import spm.project.restaurantrecommendation.entity.Role;
import spm.project.restaurantrecommendation.repository.RoleRepository;
import spm.project.restaurantrecommendation.exception.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @GetMapping("/roles")
    public List<Role> getAllRole(){return roleRepository.findAll();}

    @GetMapping("/roles/{id}")
    public Role getRoleByID(@PathVariable long id){
        return roleRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id));
    }

    @PostMapping("/roles")
    public Role addRole(@RequestBody Role role){return roleRepository.save(role);}

    @DeleteMapping
    public void deleteRole(@PathVariable long id){roleRepository.deleteById(id);}
}
