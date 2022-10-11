package com.aws.demo.rekognition;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CompareFaces {

    @Value(value = "${s3.bucket-path}")
    private String s3_bucket;

    @Value(value = "${rekognition.similarityThreshold}")
    private Float similarityThreshold;


    public boolean compareFace(String sourceImg, String targetImg) {
        String sourceImage = sourceImg;
        String targetImage = targetImg;

        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2).build();

        Image source = new Image().withS3Object((new S3Object().withName(sourceImage).withBucket(s3_bucket)));
        Image target = new Image().withS3Object((new S3Object().withName(targetImage).withBucket(s3_bucket)));
        CompareFacesRequest request = new CompareFacesRequest()
                .withSourceImage(source)
                .withTargetImage(target)
                .withSimilarityThreshold(similarityThreshold);

        // Call operation
        try {
            CompareFacesResult compareFacesResult = rekognitionClient.compareFaces(request);

            // Display results
            List<CompareFacesMatch> faceDetails = compareFacesResult.getFaceMatches();
            for (CompareFacesMatch match : faceDetails) {
                ComparedFace face = match.getFace();
                BoundingBox position = face.getBoundingBox();
                log.info("Face at " + position.getLeft().toString()
                        + " " + position.getTop()
                        + " matches with " + match.getSimilarity().toString()
                        + "% confidence.");
                return true;

            }
            List<ComparedFace> uncompared = compareFacesResult.getUnmatchedFaces();

            log.info("There was " + uncompared.size() + " face(s) that did not match");
        } catch (InvalidParameterException e) {
            log.error("Occurred InvalidParameterException : sourceImage : " + sourceImage +", targetImg : " + targetImg);
            return false;
        } catch (Exception e) {
            log.error("Occurred Exception : sourceImage : " + sourceImage +", targetImg : " + targetImg);
            return false;
        }

        return false;
    }

    public boolean detectFacesinImage(String sourceImage) {
        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2).build();

        Image source = new Image().withS3Object((new S3Object().withName(sourceImage).withBucket(s3_bucket)));

        DetectFacesRequest request = new DetectFacesRequest().withImage(source).withAttributes(Attribute.ALL);

        boolean isDetectFaces = false;

        try {
            DetectFacesResult result = rekognitionClient.detectFaces(request);
            List<FaceDetail> faceDetails = result.getFaceDetails();

            for (FaceDetail face : faceDetails) {
                if (request.getAttributes().contains("ALL")) {
                    AgeRange ageRange = face.getAgeRange();
                    log.info("The detected face is estimated to be between "
                            + ageRange.getLow().toString() + " and " + ageRange.getHigh().toString()
                            + " years old.");
                    isDetectFaces = true;
                    break;
                }
            }
        } catch (AmazonRekognitionException e) {
            e.printStackTrace();
        }
        return isDetectFaces;
    }
}
