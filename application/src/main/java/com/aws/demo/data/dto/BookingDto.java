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
    private String photoImg;

    private String bookingId;
}
