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

    @GetMapping("/total")
    public ResponseEntity<Long> getTotalPatients() {
        return ResponseEntity.ok(patientService.getTotalPatients());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Patient>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String gender
    ) {
        return ResponseEntity.ok(patientService.search(name, gender));
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody PatientRequest patientRequest) {
        return ResponseEntity.ok(patientService.savePatient(patientRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePatient(@PathVariable String id) {
        return ResponseEntity.ok(patientService.deletePatient(id));
    }

}
