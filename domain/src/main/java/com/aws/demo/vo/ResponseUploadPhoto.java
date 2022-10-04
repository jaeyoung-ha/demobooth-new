package com.aws.demo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUploadPhoto {
    private String bookingId;
    private String nickname;
    private String photoImg;
}
