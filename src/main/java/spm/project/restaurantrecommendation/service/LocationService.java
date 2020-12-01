package spm.project.restaurantrecommendation.service;

import spm.project.restaurantrecommendation.entity.Location;

import java.util.List;

// :::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu ::::::::::
// :::::::::::::::::::::::::::::::::::::::::

public interface LocationService {
    List<Location> findAll();

    Location findByDistrict(String district);

    Location findById(Long id);
}
