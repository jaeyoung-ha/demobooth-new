package com.aws.demo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCheckIn {
    private String bookingId;
    private String nickname;
    private String arrival;
    private String flightDeparture;
    private String flightArrival;
    private String checkinDate;
    private String checkoutDate;
    private String roomType;
    private Integer hotelAdult;
    private Integer hotelChild;
    private String photoImg;
}
