package com.josue.springawsdynamo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Service
public abstract class BaseDynamoDbService<T, ID> extends DynamoDbTemplate {

  @Autowired
  public BaseDynamoDbService(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
    super(dynamoDbEnhancedClient);
  }

}
