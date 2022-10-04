package com.aws.demo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBooking {
    private String bookingId;
    private String nickname;
    private String email;
    private String checkinDate;
    private String checkoutDate;
    private String roomType;
}
