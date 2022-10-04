package com.aws.demo.jpa;

import com.aws.demo.data.entity.ReservationEntity;
import com.aws.demo.data.repository.BookingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingJpaRepository extends BookingRepository, CrudRepository<ReservationEntity, Long> {

    @Override
    ReservationEntity findByNickname(String nickname);

    @Override
    ReservationEntity save(ReservationEntity reservationEntity);

    @Override
    void delete(ReservationEntity reservationEntity);

}
