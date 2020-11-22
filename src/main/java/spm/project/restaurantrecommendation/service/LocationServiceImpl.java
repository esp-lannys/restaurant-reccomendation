package spm.project.restaurantrecommendation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spm.project.restaurantrecommendation.entity.Location;
import spm.project.restaurantrecommendation.repository.LocationRepository;

import java.util.List;

// :::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu ::::::::::
// :::::::::::::::::::::::::::::::::::::::::

@Service("locationService")
public class LocationServiceImpl implements LocationService{

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location findByDistrict(String district) {
        return locationRepository.findByDistrict(district);
    }


}
