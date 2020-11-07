package spm.project.restaurantrecommendation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spm.project.restaurantrecommendation.entity.Role;
import spm.project.restaurantrecommendation.entity.User;
import spm.project.restaurantrecommendation.exception.EntityNotFoundException;
import spm.project.restaurantrecommendation.repository.RoleRepository;
import spm.project.restaurantrecommendation.repository.UserRepository;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;

    public UserController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }



    @GetMapping("/users")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/roles")
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @PostMapping("/users")
    public User registerUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser){
        return userRepository.findById(id).map((user) -> {
            user.setEmail(newUser.getEmail());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPhone(newUser.getPhone());
            user.setPassword(newUser.getPassword());
            return userRepository.save(user);
        }).orElseGet(() -> {
            newUser.setId(id);
            return userRepository.save((newUser));
        });
    }

    @PatchMapping(path = "/users/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<User> updateUserPartially(@PathVariable Long id, @RequestBody JsonPatch patch) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
            User userPatched = applyPatchToCustomer(patch, user);
            userRepository.save(userPatched);
            return ResponseEntity.ok(userPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    private User applyPatchToCustomer(
            JsonPatch patch, User targetUser) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetUser, JsonNode.class));
        return objectMapper.treeToValue(patched, User.class);
    }
}

