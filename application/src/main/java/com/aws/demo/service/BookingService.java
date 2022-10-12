package com.aws.demo.service;

import com.aws.demo.constants.PhotoConstants;
import com.aws.demo.data.dto.BookingDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookingService {
    BookingDto createReserve(BookingDto bookingDto);

    BookingDto getReserveByBookingId(String bookingId);

    BookingDto uploadPhoto(String bookingId, MultipartFile multipartFile);

    String storeImg(MultipartFile multipartFile, PhotoConstants.PhotoType photoType);

    BookingDto checkIn(MultipartFile multipartFile);

    byte[] getPhoto(String filename) throws IOException;
}
