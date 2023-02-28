package org.example.config;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.localstack.LocalStackContainer;

@TestConfiguration
public class AWSConfig {

    @Bean
    public AmazonSQSAsync amazonSQS(LocalStackContainer localStackContainer) {
        return AmazonSQSAsyncClientBuilder.standard()
            .withEndpointConfiguration(localStackContainer.getEndpointConfiguration(LocalStackContainer.Service.SQS))
            .withCredentials(localStackContainer.getDefaultCredentialsProvider())
            .build();
    }

}
