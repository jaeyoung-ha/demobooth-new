package com.aws.demo.amazons3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class AwsS3 {

    public void upload(File file, String key) {
        String s3_bucket = "demo-img-1103";
        uploadToS3(new PutObjectRequest(s3_bucket, key, file));
    }

    private void uploadToS3(PutObjectRequest putObjectRequest) {
        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(Regions.AP_NORTHEAST_2)
                    .build();

            s3Client.putObject(putObjectRequest);
            System.out.println(String.format("[%s] upload complete", putObjectRequest.getKey()));

        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
