package com.aws.demo.vo;

import lombok.Data;

@Data
public class RequestBooking {
    private String departure;

    private String departureDate;

    private String returnDate;

    private Integer passengerAdult;

    private Integer passengerChild;

    private String nickname;

    private String email;

    private String checkinDate;

    private String checkoutDate;

    private String roomType;

    private Integer hotelAdult;

    private Integer hotelChild;

    private String phoneNum;
}
