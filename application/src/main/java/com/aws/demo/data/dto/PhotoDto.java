package com.aws.demo.data.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PhotoDto implements Serializable {
    private String nickname;
    private String photoImg;
}
