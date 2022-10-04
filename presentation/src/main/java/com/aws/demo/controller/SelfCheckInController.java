package com.aws.demo.controller;

import com.aws.demo.data.dto.BookingDto;
import com.aws.demo.service.BookingService;
import com.aws.demo.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/demo-service")
public class SelfCheckInController {

    private Environment env;
    BookingService bookingService;

    @Autowired
    public SelfCheckInController(Environment env, BookingService bookingService) {
        this.env = env;
        this.bookingService = bookingService;
    }

    // http://127.0.0.1:0/demo-service/booking
    @PostMapping("/booking")
    public ResponseEntity<ResponseBooking> createReserve(@RequestBody RequestBooking bookingDetails) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        BookingDto bookingDto = mapper.map(bookingDetails, BookingDto.class);
        bookingService.createReserve(bookingDto);

        ResponseBooking responseBooking = mapper.map(bookingDto, ResponseBooking.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBooking);
    }

    // http://127.0.0.1:0/demo-service/booking/{nickname}
    @GetMapping("/booking/{nickname}")
    public ResponseEntity<ResponseBooking> getReserve(@PathVariable("nickname") String nickname) {
        BookingDto bookingDto = bookingService.getReservationByNickname(nickname);
        ResponseBooking result = new ModelMapper().map(bookingDto, ResponseBooking.class);

        return ResponseEntity.status(HttpStatus.OK).body(result);  
    }

    @PostMapping("/booking/photo/{nickname}")
    public ResponseEntity<ResponseUploadPhoto> uploadPhoto(@PathVariable("nickname") String nickname, @RequestPart("file") MultipartFile multipartFile) {
        BookingDto bookingDto = bookingService.uploadPhoto(nickname, multipartFile);
        ResponseUploadPhoto result = new ModelMapper().map(bookingDto, ResponseUploadPhoto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/checkin-air")
    public ResponseEntity<ResponseCheckInAir> checkInAir(@RequestPart("file") MultipartFile multipartFile) {
        BookingDto bookingDto = bookingService.checkIn(multipartFile);
        ResponseCheckInAir result = new ModelMapper().map(bookingDto, ResponseCheckInAir.class);

        log.info("/checkin-air - ResponseCheckInAir : " + result.getDeparture() + ", " + result.getDepartureDate());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/checkin-hotel")
    public ResponseEntity<ResponseCheckInHotel> checkInHotel(@RequestPart("file") MultipartFile multipartFile) {
        BookingDto bookingDto = bookingService.checkIn(multipartFile);
        ResponseCheckInHotel result = new ModelMapper().map(bookingDto, ResponseCheckInHotel.class);

        log.info("/checkin-hotel - ResponseCheckInHotel : " + result.getNickname() + ", " + result.getCheckinDate());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/booking/{nickname}")
    public String deleteOrder(@PathVariable("nickname") String nickname) {

        return "delete success";
    }
}
