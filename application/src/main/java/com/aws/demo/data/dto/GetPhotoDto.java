package com.aws.demo.data.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetPhotoDto implements Serializable {
    private byte[] photoImg;

    private String errCode;
    private String errMsg;
}
