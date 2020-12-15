package spm.project.restaurantrecommendation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spm.project.restaurantrecommendation.dto.ReservationDetailDto;
import spm.project.restaurantrecommendation.entity.ReservationDetail;
import spm.project.restaurantrecommendation.entity.Restaurant;
import spm.project.restaurantrecommendation.repository.ReservationDetailRepository;
import spm.project.restaurantrecommendation.repository.RestaurantRepository;

import java.util.List;
import java.util.UUID;

@Service("reservationDetailService")
@Transactional
public class ReservationDetailServiceImpl implements ReservationDetailService{
    @Autowired
    private ReservationDetailRepository reservationDetailRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;


    @Override
    public List<ReservationDetail> findAll() {
        return reservationDetailRepository.findAll();
    }

    @Override
    public ReservationDetail save(ReservationDetailDto dto, Restaurant restaurant) {
        ReservationDetail reservationDetail = new ReservationDetail();
        reservationDetail.setAdditionalRequest(dto.getAdditionalRequest());
        reservationDetail.setDate(dto.getDate());
        reservationDetail.setEmail(dto.getEmail());
        reservationDetail.setFirstName(dto.getFirstName());
        reservationDetail.setLastName(dto.getLastName());
        reservationDetail.setTime(dto.getTime());
        reservationDetail.setOccasion(dto.getOccasion());
        reservationDetail.setPhone(dto.getPhone());
        reservationDetail.setNumOfPeople(dto.getNumOfPeople());
        reservationDetail.setRestaurant(restaurant);
        reservationDetail.setUuid(UUID.randomUUID().toString());
        return reservationDetailRepository.save(reservationDetail);
    }


}
