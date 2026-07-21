package com.belle.springdatajpa.service;

import com.belle.springdatajpa.exception.UniqueConstraintException;
import com.belle.springdatajpa.model.Customer;
import com.belle.springdatajpa.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> findBySubscriptionEndsOn(LocalDate date) {
        return customerRepository.findBySubscriptionEndsOn(date);
    }

    public Optional<Customer> findByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }

    public Customer saveCustomer(Customer customer) {
        if (customerRepository.existsByPhoneNumber(customer.getPhoneNumber())) {
            throw new UniqueConstraintException("Phone number already taken.");
        }

        return customerRepository.save(customer);
    }

    public int countByIsSubscriptionActive(boolean isActive) {
        return customerRepository.countByIsSubscriptionActive(isActive);
    }

    public List<Customer> findBySubscriptionEndsOnBetween(LocalDate start, LocalDate end) {
        LocalDate today = LocalDate.now();
        List<Customer> list1 = customerRepository.findBySubscriptionEndsOnBetween(today, today.plusMonths(1));
        return customerRepository.findBySubscriptionEndsOnBetween(start, end);
    }
}
