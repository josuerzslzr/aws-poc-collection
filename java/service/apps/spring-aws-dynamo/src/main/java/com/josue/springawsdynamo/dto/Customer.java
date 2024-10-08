package com.josue.springawsdynamo.dto;

import java.util.Map;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
/*
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
*/
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;
//import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

@DynamoDbBean
public class Customer {

  private String customerKey;

  // private String entityKey;

  private String firstName;

  private String lastName;

  private Map<String, String> address;

  @DynamoDbPartitionKey
  public String getCustomerKey() {
    return customerKey;
  }

  public void setCustomerKey(String customerKey) {
    this.customerKey = customerKey;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Map<String, String> getAddress() {
    return address;
  }

  public void setAddress(Map<String, String> address) {
    this.address = address;
  }

}
