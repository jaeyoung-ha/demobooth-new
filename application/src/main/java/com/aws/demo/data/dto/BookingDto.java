package com.aws.demo.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto implements Serializable {
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

    private String errCode;
    private String errMsg;
}
