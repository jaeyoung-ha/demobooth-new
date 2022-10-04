package com.aws.demo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCheckInAir {
    private String departure;
    private String departureDate;
    private Integer passengerAdult;
    private Integer passengerChild;
}
