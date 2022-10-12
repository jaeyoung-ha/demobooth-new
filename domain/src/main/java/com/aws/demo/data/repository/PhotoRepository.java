package com.aws.demo.data.repository;

import com.aws.demo.data.entity.PhotoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends CrudRepository<PhotoEntity, Long> {

    @Query(value = "SELECT m.id, m.booking_id, m.photo_img, m.check_in FROM photo m WHERE m.check_in=0 ORDER BY m.id DESC Limit 50", nativeQuery = true)
    List<PhotoEntity> findPhotoTop50();

    PhotoEntity save(PhotoEntity reservationEntity);

    PhotoEntity findByBookingId(String bookingId);
}
