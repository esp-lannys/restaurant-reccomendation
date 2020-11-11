package spm.project.restaurantrecommendation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import spm.project.restaurantrecommendation.entity.Rating;
import spm.project.restaurantrecommendation.repository.RatingRepository;
import spm.project.restaurantrecommendation.exception.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class RatingController {
    @Autowired
    private final RatingRepository ratingRepository;

    public RatingController(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @GetMapping("/ratings")
    public List<Rating> getAllRating(){ return ratingRepository.findAll();}

    @GetMapping("/rating/{id}")
    public Rating getRatingByID(@PathVariable long id){
        return ratingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @PostMapping("/ratings")
    public Rating addRating(@RequestBody Rating rating){
        return ratingRepository.save(rating);
    }

    @DeleteMapping("/ratings/{id}")
    public void deleteRating(@PathVariable long id){ratingRepository.deleteById(id);}

}
