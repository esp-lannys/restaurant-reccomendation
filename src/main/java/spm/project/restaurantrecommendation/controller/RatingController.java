package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import spm.project.restaurantrecommendation.entity.Rating;
import spm.project.restaurantrecommendation.repository.RatingRepository;

import java.util.List;

@RestController
public class RatingController {
    @Autowired
    private final RatingRepository ratingRepository;

    public RatingController(RatingRepository ratingRepository){
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> getAllRating(){
        return ratingRepository.findAll();
    }
}