package com.capabank.capabank.controller;

import com.capabank.capabank.model.Customer;
import com.capabank.capabank.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")

public class CustomerController {
private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
       return customerService.createCustomer(customer);

    }
}


