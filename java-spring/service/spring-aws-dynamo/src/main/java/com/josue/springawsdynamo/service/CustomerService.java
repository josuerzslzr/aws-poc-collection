package com.josue.springawsdynamo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josue.springawsdynamo.dto.Customer;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

@Service
public class CustomerService {

  @Autowired
  DynamoDbTemplate dynamoDbTemplate;
  /*
   * @Autowired
   * DynamoDbEnhancedClient dynamoDbEnhancedClient;
   * 
   * DynamoDbTable<Customer> table;
   * 
   * public CustomerService() {
   * super();
   * table = dynamoDbEnhancedClient.table("Customer",
   * TableSchema.fromBean(Customer.class));
   * }
   */

  public Customer save(Customer customer) {
    System.out.println("naaah this is crap: " + customer.getCustomerKey());
    return dynamoDbTemplate.save(customer);
  }

  public Customer update(Customer customer) {
    return dynamoDbTemplate.update(customer);
  }

  public void delete(Customer customer) {
    dynamoDbTemplate.delete(customer);
  }

  public Customer getOne(String customerKey) {
    Key key = Key.builder().partitionValue(customerKey).build();
    return dynamoDbTemplate.load(key, Customer.class);
  }

  public PageIterable<Customer> getAll(String customerKey) {
    return dynamoDbTemplate.scanAll(Customer.class);
  }

}
