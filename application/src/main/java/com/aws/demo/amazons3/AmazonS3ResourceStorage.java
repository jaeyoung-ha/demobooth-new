package com.aws.demo.amazons3;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.aws.demo.utils.MultipartUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Component
@Slf4j
@RequiredArgsConstructor
public class AmazonS3ResourceStorage {

    @Value(value = "${s3.bucket-path}")
    private String bucket;

    public String store(MultipartFile multipartFile) {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();

        String fileName = MultipartUtil.createFileName(multipartFile.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType((multipartFile.getContentType()));

        log.info("store() fileName : " + fileName);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            s3Client.putObject(bucket, fileName, inputStream, objectMetadata);
        } catch (IOException e) {
            //throw new IllegalArgumentException(String.format("Exception : %s", e.getMessage()));
            log.error("store() - occurred IllegalArgumentException");
            fileName = "";
        }

        return fileName;
    }

    public byte[] getFile(String filename) throws IOException {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();

        byte[] result = new byte[0];

        try {
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, filename);
            S3Object s3Object = s3Client.getObject(getObjectRequest);
            S3ObjectInputStream objectInputStream = s3Object.getObjectContent();

            result = IOUtils.toByteArray(objectInputStream);
        } catch (AmazonS3Exception e) {
            log.error("getFile() - occurred AmazonS3Exception");
            return new byte[0];
        }


        return result;
    }

    /*
    private String createFileName(String originFileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(originFileName));
   }

   private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));

        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("Invalid file %s", e.getMessage()));
        }
   }*/

}
