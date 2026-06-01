package com.jaya.patientclinicals.clinicalsapi.clinicalsapi.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.jaya.patientclinicals.clinicalsapi.clinicalsapi.models.Patient;
import com.jaya.patientclinicals.clinicalsapi.clinicalsapi.repos.PatientRepository;
import com.jaya.patientclinicals.clinicalsapi.clinicalsapi.exceptions.PatientNotFoundException;

@RestController
@RequestMapping("/api")
public class PatientController {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @PostMapping("/patients")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient saved = patientRepository.save(patient);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientRepository.findAll());
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new PatientNotFoundException(id));
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
        existing.setFirstName(patient.getFirstName());
        existing.setLastName(patient.getLastName());
        existing.setAge(patient.getAge());
        Patient updated = patientRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable Long id) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
        patientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
