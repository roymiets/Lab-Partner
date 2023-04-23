package com.cm.tdbPublisher.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SQSConfig {

    @Value("${app.config.aws.access_key_id}")
    private String awsAccessKeyId;
    @Value("${app.config.aws.secret_key_id}")
    private String awsSecretKeyId;

    @Bean
    public AmazonSQS amazonSQSClient() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKeyId, awsSecretKeyId);
        return AmazonSQSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.AP_SOUTH_1)
                .build();
    }
}
