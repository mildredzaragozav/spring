package com.belle.springdatamongodb.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patients")
@Data
@Builder
public class Patient {
    @Id
    private String id;
    private PatientPersonalInformation personalInformation;

    public Patient(String id, PatientPersonalInformation personalInformation) {
        this.id = id;
        this.personalInformation = personalInformation;
    }

    public Patient(){}
}
