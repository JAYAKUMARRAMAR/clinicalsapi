package com.jaya.patientclinicals.clinicalsapi.clinicalsapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jaya.patientclinicals.clinicalsapi.clinicalsapi.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
