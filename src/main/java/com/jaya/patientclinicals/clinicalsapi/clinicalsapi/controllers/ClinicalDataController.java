package com.jaya.patientclinicals.clinicalsapi.clinicalsapi.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.jaya.patientclinicals.clinicalsapi.clinicalsapi.models.ClinicalData;
import com.jaya.patientclinicals.clinicalsapi.clinicalsapi.repos.ClinicalDataRepository;
import com.jaya.patientclinicals.clinicalsapi.clinicalsapi.repos.PatientRepository;

@RestController
@RequestMapping("/api")
public class ClinicalDataController {
    
    private final ClinicalDataRepository clinicalDataRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public ClinicalDataController(ClinicalDataRepository clinicalDataRepository, PatientRepository patientRepository) {
        this.clinicalDataRepository = clinicalDataRepository;
        this.patientRepository = patientRepository;
    }

    @PostMapping("/clinicaldata")
    public ResponseEntity<ClinicalData> createClinicalData(@RequestBody ClinicalData clinicalData) {
        ClinicalData saved = clinicalDataRepository.save(clinicalData);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/clinicaldata")
    public List<ClinicalData> getAllClinicalData() {
        return clinicalDataRepository.findAll();
    }

    @GetMapping("/clinicaldata/{id}")
    public ResponseEntity<ClinicalData> getClinicalData(@PathVariable Long id) {
        return clinicalDataRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/clinicaldata/{id}")
    public ResponseEntity<ClinicalData> updateClinicalData(@PathVariable Long id, @RequestBody ClinicalData clinicalData) {
        return clinicalDataRepository.findById(id).map(existing -> {
            existing.setComponentName(clinicalData.getComponentName());
            existing.setComponentValue(clinicalData.getComponentValue());
            existing.setMeasuredDateTime(clinicalData.getMeasuredDateTime());
            ClinicalData updated = clinicalDataRepository.save(existing);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/clinicaldata/{id}")
    public ResponseEntity<Object> deleteClinicalData(@PathVariable Long id) {
        return clinicalDataRepository.findById(id).map(existing -> {
            clinicalDataRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    // method that receives patient id in the URL and clinical data in the body, then saves it
    @PostMapping("/clinicals/{patientId}")
    public ResponseEntity<ClinicalData> saveClinicalData(@PathVariable Long patientId, @RequestBody ClinicalData clinicalData) {
        return patientRepository.findById(patientId).map(patient -> {
            clinicalData.setPatient(patient);
            ClinicalData saved = clinicalDataRepository.save(clinicalData);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }).orElse(ResponseEntity.notFound().build());
    }

}
