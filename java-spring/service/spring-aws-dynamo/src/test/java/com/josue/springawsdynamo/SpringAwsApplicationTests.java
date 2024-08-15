package com.josue.springawsdynamo;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.josue.springawsdynamo.dto.Customer;
import com.josue.springawsdynamo.service.CustomerService;

@SpringBootTest
class SpringAwsApplicationTests {

  // @Autowired
  // private AmazonDynamoDB amazonDynamoDB;

  // @Autowired
  // CustomerRepository repository;

  @Autowired
  CustomerService service;

  @Test
  public void saveItem() {
    // Create Address
    HashMap<String, String> address = new HashMap<>();
    address.put("road", "456 Nowhere Lane");
    address.put("city", "Langely");
    address.put("state", "WS");
    address.put("postalCode", "98260");
    address.put("country", "USA");

    Customer cust = new Customer();
    cust.setCustomerKey("111");
    cust.setAddress(address);
    cust.setFirstName("Satoshi");
    cust.setLastName("Nakamoto");
    service.save(cust);
    // List<Customer> result = (List<Customer>) service.findAll();

  }

}
