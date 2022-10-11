package com.aws.demo.constants;

public final class StatusCodeConstants {
    public static final String OK = "OK";
    // HTTP 상태코드 200
    public static String okCodeRequestSuccess = "20005000";

    public static String okDescRequestSuccess = "Success response";
    public static String okCodeRequestAlreadySigned = "20005001";
    public static String okDescRequestAlreadySigned = "이미 처리함";

    // HTTP 상태코드 400
    public static String badRequestCodeInvalidHeader = "40005000";
    public static String badRequestDescInvalidHeader = "유효하지 않은 Header";
    public static String badRequestCodeInvalidParam = "40005001";
    public static String badRequestDescInvalidParam = "Invalid parameter from uploaded image";

    // HTTP 상태코드 500
    public static String serverErrorCodeStorageUploadFail = "50005000";
    public static String serverErrorDescStorageUploadFail = "Failed to save image";
    public static String serverErrorCodeRekognitionMatchFail = "50005001";
    public static String serverErrorDescRekognitionMatchFail = "Failed to match for Rekognition";

}
