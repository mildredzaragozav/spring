package com.belle.springdatamongodb.service;

import com.belle.springdatamongodb.model.Patient;
import com.belle.springdatamongodb.model.PatientPersonalInformation;
import com.belle.springdatamongodb.model.PatientRequest;
import com.belle.springdatamongodb.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public Long getTotalPatients() {
        return patientRepository.count();
    }

    public Patient savePatient(PatientRequest patientRequest) {
        PatientPersonalInformation personalInformation = PatientPersonalInformation
                .builder()
                .fullName(patientRequest.name())
                .dateOfBirth(patientRequest.dateOfBirth())
                .gender(patientRequest.gender())
                .maritalStatus(patientRequest.maritalStatus())
                .build();
        Patient newPatient = Patient.builder().personalInformation(personalInformation).build();

        return patientRepository.save(newPatient);
    }

    public boolean deletePatient(String id) {
        patientRepository.deleteById(id);
        return true;
    }

    /* not available yet*/
    public List<Patient> search(String name, String gender) {
        //if (StringUtils.hasText(name) && StringUtils.hasText(gender)) {
        //    patientRepository.searchByNameAndGender(name, gender);
        //} else
        if (StringUtils.hasText(name)) {
            return patientRepository.findByPersonalInformation_FullNameContainingIgnoreCase(name);
        }

        /*else if (StringUtils.hasText(gender)) {
            return patientRepository.findAllByGender(gender);
        } */

        return null;
    }

}
