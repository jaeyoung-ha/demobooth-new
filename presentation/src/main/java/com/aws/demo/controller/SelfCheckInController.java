package com.aws.demo.controller;

import com.aws.demo.constants.StatusCodeConstants;
import com.aws.demo.data.dto.BookingDto;
import com.aws.demo.data.dto.CommonReturnDto;
import com.aws.demo.service.BookingService;
import com.aws.demo.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.TextUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public ResponseEntity<CommonReturnDto<ResponseBooking>> createReserve(@RequestBody RequestBooking bookingDetails) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        BookingDto bookingDto = mapper.map(bookingDetails, BookingDto.class);
        bookingService.createReserve(bookingDto);

        ResponseBooking responseBooking = mapper.map(bookingDto, ResponseBooking.class);

        return new ResponseEntity<>(
                CommonReturnDto.<ResponseBooking>builder()
                        .statusCode(TextUtils.isEmpty(bookingDto.getErrCode()) ? StatusCodeConstants.okCodeRequestSuccess : bookingDto.getErrCode())
                        .statusMsg(TextUtils.isEmpty(bookingDto.getErrMsg()) ? StatusCodeConstants.okDescRequestSuccess : bookingDto.getErrMsg())
                        .data(responseBooking)
                        .build(),
                HttpStatus.OK);
    }

    // http://127.0.0.1:0/demo-service/booking/{nickname}
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<CommonReturnDto<ResponseBooking>> getReserve(@PathVariable("bookingId") String bookingId) {
        BookingDto bookingDto = bookingService.getReserveByBookingId(bookingId);
        ResponseBooking responseBooking = new ModelMapper().map(bookingDto, ResponseBooking.class);

        return new ResponseEntity<>(
                CommonReturnDto.<ResponseBooking>builder()
                        .statusCode(TextUtils.isEmpty(bookingDto.getErrCode()) ? StatusCodeConstants.okCodeRequestSuccess : bookingDto.getErrCode())
                        .statusMsg(TextUtils.isEmpty(bookingDto.getErrMsg()) ? StatusCodeConstants.okDescRequestSuccess : bookingDto.getErrMsg())
                        .data(responseBooking)
                        .build(),
                HttpStatus.OK);
    }

    @PostMapping("/booking/photo/{bookingId}")
    public ResponseEntity<CommonReturnDto<ResponseUploadPhoto>> uploadPhoto(@PathVariable("bookingId") String bookingId, @RequestPart("file") MultipartFile multipartFile) {
        BookingDto bookingDto = bookingService.uploadPhoto(bookingId, multipartFile);
        ResponseUploadPhoto result = new ModelMapper().map(bookingDto, ResponseUploadPhoto.class);

        return new ResponseEntity<>(
                CommonReturnDto.<ResponseUploadPhoto>builder()
                        .statusCode(TextUtils.isEmpty(bookingDto.getErrCode()) ? StatusCodeConstants.okCodeRequestSuccess : bookingDto.getErrCode())
                        .statusMsg(TextUtils.isEmpty(bookingDto.getErrMsg()) ? StatusCodeConstants.okDescRequestSuccess : bookingDto.getErrMsg())
                        .data(result)
                        .build(),
                HttpStatus.OK);
    }

    @PostMapping("/checkin")
    public ResponseEntity<CommonReturnDto<ResponseCheckIn>> checkIn(@RequestPart("file") MultipartFile multipartFile) {
        BookingDto bookingDto = bookingService.checkIn(multipartFile);
        ResponseCheckIn result = new ModelMapper().map(bookingDto, ResponseCheckIn.class);

        return new ResponseEntity<>(
                CommonReturnDto.<ResponseCheckIn>builder()
                        .statusCode(TextUtils.isEmpty(bookingDto.getErrCode()) ? StatusCodeConstants.okCodeRequestSuccess : bookingDto.getErrCode())
                        .statusMsg(TextUtils.isEmpty(bookingDto.getErrMsg()) ? StatusCodeConstants.okDescRequestSuccess : bookingDto.getErrMsg())
                        .data(result)
                        .build(),
                HttpStatus.OK);
    }

    @PostMapping("/checkin-hotel")
    public ResponseEntity<ResponseCheckInHotel> checkInHotel(@RequestPart("file") MultipartFile multipartFile) {
        BookingDto bookingDto = bookingService.checkIn(multipartFile);
        ResponseCheckInHotel result = new ModelMapper().map(bookingDto, ResponseCheckInHotel.class);

        log.info("/checkin-hotel - ResponseCheckInHotel : " + result.getNickname() + ", " + result.getCheckinDate());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/getPhoto/{filename}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable("filename") String filename) {
        log.info("/getPhoto - filename : " + filename);

        byte[] photoImg = new byte[0];

        try {
            photoImg = bookingService.getPhoto(filename);
        } catch (IOException e) {
            log.error("/getPhoto occurred IOException");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(photoImg, headers, HttpStatus.OK);
    }

/*
    @GetMapping("/getPhoto/{filename}")
    public ResponseEntity<CommonReturnDto<ResponseGetPhoto>> getPhoto(@PathVariable("filename") String filename) {
        GetPhotoDto getPhotoDto = new GetPhotoDto();

        log.info("/getPhoto - filename : " + filename);

        try {
            getPhotoDto.setPhotoImg(bookingService.getPhoto(filename));
        } catch (IOException e) {
            log.error("/getPhoto occurred IOException");
            getPhotoDto.setErrCode(StatusCodeConstants.serverErrorCodeStorageDownloadFail);
            getPhotoDto.setErrCode(StatusCodeConstants.serverErrorDescStorageDownloadFail);
        }
        ResponseGetPhoto responseGetPhoto = new ModelMapper().map(getPhotoDto, ResponseGetPhoto.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(
                CommonReturnDto.<ResponseGetPhoto>builder()
                        .statusCode(TextUtils.isEmpty(getPhotoDto.getErrCode()) ? StatusCodeConstants.okCodeRequestSuccess : getPhotoDto.getErrCode())
                        .statusMsg(TextUtils.isEmpty(getPhotoDto.getErrMsg()) ? StatusCodeConstants.okDescRequestSuccess : getPhotoDto.getErrMsg())
                        .data(responseGetPhoto)
                        .build(),
                headers,
                HttpStatus.OK);

//        ResponseEntity<byte[]> result = new ResponseEntity<>(getPhotoDto.getPhotoImg(), headers, HttpStatus.OK);
//        return result;
    }
 */


    @DeleteMapping("/booking/{nickname}")
    public String deleteOrder(@PathVariable("nickname") String nickname) {

        return "delete success";
    }
}
