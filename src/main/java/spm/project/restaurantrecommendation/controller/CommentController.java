package spm.project.restaurantrecommendation.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import spm.project.restaurantrecommendation.repository.CommentsRepository;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private final CommentsRepository commentsRepository;

    public CommentController(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }
}