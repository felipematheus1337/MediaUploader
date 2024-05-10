package com.mediaupload.spring.infra.config.aws;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSBeanConfiguration {



    @Value("${aws-accessKeyId}")
    private String accessKey;


    @Value("${aws-secretKey}")
    private String secretKey;

    @Value("${aws-region}")
    private String region;


    @Bean
    public AmazonS3 s3Config() {

        BasicAWSCredentials awsCredentials = awsCredentials();

        return AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    @Bean
    public AmazonSNS amazonSNSClient() {

        BasicAWSCredentials awsCredentials = awsCredentials();

        return AmazonSNSClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

    }

    public BasicAWSCredentials awsCredentials() {

        return new BasicAWSCredentials(accessKey, secretKey);
    }
}
