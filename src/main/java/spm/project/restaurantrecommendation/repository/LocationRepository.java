package spm.project.restaurantrecommendation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spm.project.restaurantrecommendation.entity.Location;

// :::::::::::::::::::::::::::::::::::::::::
// :::::::::: author : @nphoangtu ::::::::::
// :::::::::::::::::::::::::::::::::::::::::

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByDistrict(String district);
}
