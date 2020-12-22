package spm.project.restaurantrecommendation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spm.project.restaurantrecommendation.entity.Location;
import spm.project.restaurantrecommendation.repository.LocationRepository;

import java.util.List;


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

    @Override
    public Location findById(Long id) {
        return locationRepository.findById(id).get();
    }

    @Override
    public List<Location> findLocationByRestaurantId(Long id) {
        return locationRepository.findLocationByRestaurantId(id);
    }


}
