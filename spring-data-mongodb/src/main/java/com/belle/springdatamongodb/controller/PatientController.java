package com.belle.springdatamongodb.controller;

import com.belle.springdatamongodb.model.Patient;
import com.belle.springdatamongodb.model.PatientRequest;
import com.belle.springdatamongodb.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Patient>> getPatientByName(@PathVariable String name) {
        return ResponseEntity.ok(patientService.getPatientByName(name));
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotalPatients() {
        return ResponseEntity.ok(patientService.getTotalPatients());
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody PatientRequest patientRequest) {
        return ResponseEntity.ok(patientService.savePatient(patientRequest));
    }

}
