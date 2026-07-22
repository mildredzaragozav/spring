package com.belle.springdatajpa.controller;

import com.belle.springdatajpa.model.Customer;
import com.belle.springdatajpa.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Customer>> getCustomersSortedByRegistrationDate() {
        return ResponseEntity.ok(customerService.sortCustomerByRegistrationDateDesc());
    }
}
