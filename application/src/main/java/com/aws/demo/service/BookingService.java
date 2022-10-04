package com.aws.demo.service;

import com.aws.demo.constants.PhotoConstants;
import com.aws.demo.data.dto.BookingDto;
import org.springframework.web.multipart.MultipartFile;

public interface BookingService {
    BookingDto createReserve(BookingDto bookingDto);

    BookingDto getReservationByNickname(String nickname);

    BookingDto uploadPhoto(String nickname, MultipartFile multipartFile);

    String storeImg(MultipartFile multipartFile, PhotoConstants.PhotoType photoType);

    BookingDto checkIn(MultipartFile multipartFile);
}
