package com.aws.demo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCheckInHotel {
    private String nickname;
    private String email;
    private String checkinDate;
    private String checkoutDate;
    private String roomType;
}
