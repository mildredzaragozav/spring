package com.belle.springdatamongodb.service;

import com.belle.springdatamongodb.model.Patient;
import com.belle.springdatamongodb.model.PatientRequest;
import com.belle.springdatamongodb.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> getPatientByName(String name) {
        return patientRepository.findByName(name);
    }

    public List<Patient> getPatientsByGender(String gender) {
        return patientRepository.findAllByGender(gender);
    }

    public Long getTotalPatients() {
        return patientRepository.count();
    }

    public Patient savePatient(PatientRequest patientRequest) {
        Patient newPatient = new Patient();
        newPatient.setName(patientRequest.name());
        newPatient.setGender(patientRequest.gender());
        return patientRepository.save(newPatient);
    }

    public boolean deletePatient(String id) {
        patientRepository.deleteById(id);
        return true;
    }

}
