package spm.project.restaurantrecommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spm.project.restaurantrecommendation.entity.Comments;

public interface CommentsRepository extends JpaRepository<Comments,Long> { }
