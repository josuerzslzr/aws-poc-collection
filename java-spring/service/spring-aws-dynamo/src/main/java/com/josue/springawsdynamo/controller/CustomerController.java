package com.josue.springawsdynamo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josue.springawsdynamo.dto.Customer;
import com.josue.springawsdynamo.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

  @Autowired
  CustomerService customerService;

  @PostMapping
  public ResponseEntity<?> save(@RequestBody Customer customer) {
    try {
      customerService.save(customer);
      return ResponseEntity.ok("ok");
    } catch (Exception e) {
      return ResponseEntity.status(500).body("valio verga su request estimadisimo: " + e.getMessage());
    }
  }

}
