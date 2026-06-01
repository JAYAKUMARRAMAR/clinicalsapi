package com.jaya.patientclinicals.clinicalsapi.clinicalsapi.exceptions;

public class PatientNotFoundException extends RuntimeException {
    private final Long patientId;

    public PatientNotFoundException(Long id) {
        super("Patient not found with id: " + id);
        this.patientId = id;
    }

    public Long getPatientId() {
        return patientId;
    }
}
