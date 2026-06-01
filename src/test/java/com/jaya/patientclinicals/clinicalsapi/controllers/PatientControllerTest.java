package com.jaya.patientclinicals.clinicalsapi.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import com.jaya.patientclinicals.clinicalsapi.clinicalsapi.controllers.PatientController;
import com.jaya.patientclinicals.clinicalsapi.clinicalsapi.models.Patient;
import com.jaya.patientclinicals.clinicalsapi.clinicalsapi.repos.PatientRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientControllerTest {

    @Mock
    private PatientRepository patientRepository;

    private PatientController patientController;

    @BeforeEach
    void setUp() {
        patientController = new PatientController(patientRepository);
    }

    @Test
    void getAllPatients_returnsListOfPatients() {
        Patient patient1 = mock(Patient.class);
        Patient patient2 = mock(Patient.class);
        List<Patient> expectedPatients = List.of(patient1, patient2);

        when(patientRepository.findAll()).thenReturn(expectedPatients);

        List<Patient> actualPatients = (List<Patient>) patientController.getAllPatients();

        assertNotNull(actualPatients);
        assertEquals(2, actualPatients.size());
        assertSame(expectedPatients, actualPatients);
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void getAllPatients_returnsEmptyListWhenNoPatientsExist() {
        when(patientRepository.findAll()).thenReturn(Collections.emptyList());

        List<Patient> actualPatients = (List<Patient>) patientController.getAllPatients();

        assertNotNull(actualPatients);
        assertTrue(actualPatients.isEmpty());
        verify(patientRepository, times(1)).findAll();
    }
}