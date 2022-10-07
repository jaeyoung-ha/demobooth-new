package com.aws.demo.service;

import com.aws.demo.amazons3.AmazonS3ResourceStorage;
import com.aws.demo.constants.PhotoConstants;
import com.aws.demo.data.dto.BookingDto;
import com.aws.demo.data.entity.PhotoEntity;
import com.aws.demo.data.entity.ReservationEntity;
import com.aws.demo.data.repository.BookingRepository;
import com.aws.demo.data.repository.PhotoRepository;
import com.aws.demo.rekognition.CompareFaces;
import com.aws.demo.utils.DtoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    BookingRepository bookingRepository;

    PhotoRepository photoRepository;
    CompareFaces compareFaces;

    AmazonS3ResourceStorage amazonS3ResourceStorage;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, PhotoRepository photoRepository, CompareFaces compareFaces, AmazonS3ResourceStorage amazonS3ResourceStorage) {
        this.bookingRepository = bookingRepository;
        this.photoRepository = photoRepository;
        this.compareFaces = compareFaces;
        this.amazonS3ResourceStorage = amazonS3ResourceStorage;
    }

    @Override
    public BookingDto createReserve(BookingDto bookingDto) {
        bookingDto.setBookingId(UUID.randomUUID().toString());
        ReservationEntity reservationEntity;

        // ToDo. 닉네임 or 이메일 중복체크
        log.info("booking is started");

        reservationEntity = DtoUtil.convertToReserveEntity(bookingDto);
        bookingRepository.save(reservationEntity);

        BookingDto returnDto = DtoUtil.convertToReserveDto(reservationEntity);

        log.info("booking is completed");

        return returnDto;
    }

    @Override
    public BookingDto getReserveByBookingId(String bookingId) {
        ReservationEntity reservationEntity = bookingRepository.findByBookingId(bookingId);
        BookingDto bookingDto = DtoUtil.convertToReserveDto(reservationEntity);

        return bookingDto;
    }

    @Override
    public BookingDto uploadPhoto(String bookingId, MultipartFile multipartFile) {
        ReservationEntity reservationEntity = bookingRepository.findByBookingId(bookingId);

        if (reservationEntity == null) {
            return null;
        }

        log.info("uploadPhoto - start");
        // 1. Store Image file to S3
        String fileName = storeImg(multipartFile, PhotoConstants.PhotoType.REGISTER);

        log.info("uploadPhoto - s3 upload complete");

        // 2. Update DB - photo Img
        reservationEntity.setPhotoImg(fileName);
        bookingRepository.save(reservationEntity);

        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setBookingId(bookingId);
        photoEntity.setPhotoImg(fileName);
        photoRepository.save(photoEntity);


        log.info("uploadPhoto - db update complete");

        BookingDto returnDto = DtoUtil.convertToReserveDto(reservationEntity);

        log.info("uploadPhoto - fully complete");

        return returnDto;
    }

    @Override
    public String storeImg(MultipartFile multipartFile, PhotoConstants.PhotoType photoType) {
//        FileDetail fileDetail = FileDetail.multipartOf(multipartFile, photoType);
//        log.info("storeImg - fileDetail.getPath() : " + fileDetail.getPath());
//        amazonS3ResourceStorage.store(fileDetail.getPath(), multipartFile);

        String fileName = amazonS3ResourceStorage.store(multipartFile);

        return fileName;
    }

    @Override
    public BookingDto checkIn(MultipartFile multipartFile) {
        // 1. Check-In - Upload Photo
        String fileName = storeImg(multipartFile, PhotoConstants.PhotoType.CHECKIN_AIR);
        log.info("checkIn - sourceFile : " + fileName);

        //2. Compare Face
        boolean isCompared = false;

        List<PhotoEntity> photoList = photoRepository.findPhotoTop50();
        String resultBookingId = "";
        String resultPhotoImg = "";


        for (PhotoEntity entity : photoList) {
            resultPhotoImg = entity.getPhotoImg();
            log.info("checkIn - targetFile : " + resultPhotoImg);
            isCompared = compareFaces.compareFace(fileName, resultPhotoImg);

            if (isCompared) {
                log.info("checkIn - success");
                resultBookingId = entity.getBookingId();
                break;
            }
        }

        // 3. Query DB
        ReservationEntity reservationEntity = bookingRepository.findByBookingId(resultBookingId);
        BookingDto bookingDto = DtoUtil.convertToReserveDto(reservationEntity);

        return bookingDto;
    }
}

