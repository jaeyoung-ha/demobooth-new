package com.aws.demo.constants;

public final class StatusCodeConstants {
    public static final String OK = "OK";

    public static String okCodeRequestSuccess = "20005000";
    public static String okDescRequestSuccess = "Success response";

    public static String badRequestCodeInvalidParam = "40005000";
    public static String badRequestDescInvalidParam = "Invalid parameter from uploaded image";

    // HTTP 상태코드 500
    public static String serverErrorCodeStorageUploadFail = "50005000";
    public static String serverErrorDescStorageUploadFail = "Failed to save image";
    public static String serverErrorCodeRekognitionMatchFail = "50005001";
    public static String serverErrorDescRekognitionMatchFail = "Failed to match for Rekognition";

    public static String serverErrorCodeDatabaseFail = "50005002";
    public static String serverErrorDescDatabaseFail = "Failed to load from database";

    public static String serverErrorCodeStorageDownloadFail = "50005003";
    public static String serverErrorDescStorageDownloadFail = "Failed to get image";

}
