package com.belle.springdatamongodb.repository;

import com.belle.springdatamongodb.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PatientRepository extends MongoRepository<Patient, String> {
    List<Patient> findByName(String name);
    List<Patient> findAllByGender(String gender);
    @Query("{ $and: [ " +
            "{ $or: [ { 'name': { $regex: ?0, $options: 'i' } }, { $where: '?0 == null' } ] }, " +
            "{ $or: [ { 'gender': ?1 }, { $where: '?1 == null' } ] } " +
            "] }")
    List<Patient> searchByNameAndGender(String name, String gender);
}
