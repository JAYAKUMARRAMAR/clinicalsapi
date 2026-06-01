package com.jaya.patientclinicals.clinicalsapi.clinicalsapi.dto;

public class ClinicalDataRequest {
    private String componentName;
    private String componentValue;
    private Long patientId;

    // Getters and setters
    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentValue() {
        return componentValue;
    }

    public void setComponentValue(String componentValue) {
        this.componentValue = componentValue;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
