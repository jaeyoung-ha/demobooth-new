package com.aws.demo.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
@Builder
public class CommonReturnDto<T> {
    // "응답코드", example = "20003000"
    private String statusCode;

    // "응답 메시지", example = "요청 성공.")
    private String statusMsg;

    // "응답 결과"
    private T data;
}
