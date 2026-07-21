package com.belle.springdatajpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private LocalDate registrationDate;
    private LocalDate subscriptionEndsOn;
    private boolean isSubscriptionActive;
}
