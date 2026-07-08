package com.belle.springdatamongodb.model;


import jakarta.validation.constraints.NotBlank;

public record PatientRequest(@NotBlank String name, String gender) {
}
