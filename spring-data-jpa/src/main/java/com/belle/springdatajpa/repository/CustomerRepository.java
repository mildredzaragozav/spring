package com.belle.springdatajpa.repository;

import com.belle.springdatajpa.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>, PagingAndSortingRepository<Customer, Long> {
    List<Customer> findBySubscriptionEndsOn(LocalDate date);
    List<Customer> findBySubscriptionEndsOnBetween(LocalDate begin, LocalDate end);
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<Customer> findByPhoneNumber(String phoneNumber);
    int countByIsSubscriptionActive(boolean isActive);
}
