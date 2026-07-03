package com.belle.springdatamongodb.repository;

import com.belle.springdatamongodb.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PatientRepository extends MongoRepository<Patient, String> {
    List<Patient> findByName(String name);
    List<Patient> findAllByGender(String gender);
}
