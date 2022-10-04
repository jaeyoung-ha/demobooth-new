package com.aws.demo.data.repository;

import com.aws.demo.data.entity.ReservationEntity;

public interface BookingRepository {
    ReservationEntity findByNickname(String nickname);

    ReservationEntity save(ReservationEntity reservationEntity);

    void delete(ReservationEntity reservationEntity);

}
