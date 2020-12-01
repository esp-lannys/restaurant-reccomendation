package spm.project.restaurantrecommendation.service;

import spm.project.restaurantrecommendation.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);

    void delete(Long id);

    List<Category> findCategoryByIdRestaurant(Long id);
}
