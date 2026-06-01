package com.jaya.patientclinicals.clinicalsapi.clinicalsapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jaya.patientclinicals.clinicalsapi.clinicalsapi.models.ClinicalData;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Long> {
}
