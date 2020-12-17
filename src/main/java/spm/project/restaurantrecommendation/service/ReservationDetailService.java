package spm.project.restaurantrecommendation.service;
import spm.project.restaurantrecommendation.dto.ReservationDetailDto;
import spm.project.restaurantrecommendation.entity.ReservationDetail;
import spm.project.restaurantrecommendation.entity.Restaurant;

import java.util.List;

public interface ReservationDetailService {
    List<ReservationDetail> findAll();
    ReservationDetail save(ReservationDetailDto reservationDetailDto, Restaurant restaurant);

}
