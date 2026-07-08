package com.belle.springdatamongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientPersonalInformation {
    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String maritalStatus;
    private int age;

}
