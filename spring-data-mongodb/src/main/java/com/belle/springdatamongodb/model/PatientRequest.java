package com.belle.springdatamongodb.model;


import jakarta.validation.constraints.NotBlank;

public record PatientRequest(@NotBlank String name, @NotBlank String gender, @NotBlank String dateOfBirth, String maritalStatus) {
}
