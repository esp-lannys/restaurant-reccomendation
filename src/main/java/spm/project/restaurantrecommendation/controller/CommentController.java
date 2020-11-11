package spm.project.restaurantrecommendation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import spm.project.restaurantrecommendation.entity.Comments;
import spm.project.restaurantrecommendation.entity.Rating;
import spm.project.restaurantrecommendation.repository.CommentsRepository;
import spm.project.restaurantrecommendation.exception.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private final CommentsRepository commentsRepository;
    public CommentController(CommentsRepository commentsRepository){
        this.commentsRepository = commentsRepository;
    }

    @GetMapping("/comments")
    public List<Comments> getAllComments(){return commentsRepository.findAll();}

    @GetMapping("/comments/{id}")
    public Comments getCommentByID(@PathVariable long id){
        return commentsRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(id));
    }

    @PostMapping("/comments")
    public Comments addComment(@RequestBody Comments comment){
        return commentsRepository.save(comment);
    }

    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable long id){commentsRepository.deleteById(id);}
}
