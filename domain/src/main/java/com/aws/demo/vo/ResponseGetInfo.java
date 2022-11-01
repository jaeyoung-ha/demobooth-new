package com.aws.demo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseGetInfo {
    private String bookingId;
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
    private String photoImg;
    private Float similarity;
    private String password;
}
