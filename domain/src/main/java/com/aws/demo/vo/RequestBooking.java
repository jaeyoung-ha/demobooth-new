package com.aws.demo.vo;

import lombok.Data;

@Data
public class RequestBooking {
    private String nickname;

    private String departure;

    private String arrival;

    private String departureDate;

    private String returnDate;

    private Integer passengerAdult;

    private Integer passengerChild;

    private String flightDeparture;

    private String flightArrival;

    private String checkinDate;

    private String checkoutDate;

    private String roomType;

    private Integer hotelAdult;

    private Integer hotelChild;
}
