package com.jadedtdt.artificer.config;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DDBMapperConfig {

    @Value(value = "artificer")
    String projectName;

    @Bean
    AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new ProfileCredentialsProvider(projectName))
                .withRegion(Regions.US_EAST_1)
                .build();
        return client;
    }

    @Bean
    DynamoDBMapper dynamoDBMapper() {
        DynamoDBMapper ddbMapper = new DynamoDBMapper(this.amazonDynamoDB());
        return ddbMapper;
    }
}
