package com.example.proiect.service;

import com.example.proiect.exception.PatientNotFoundException;
import com.example.proiect.model.Patient;
import com.example.proiect.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    public void testCreatePatient() {
        Patient patient = new Patient("John", "Doe", "1234567891234", new Date(2000, 4, 5), 60, 1.70);
        Patient savedPatient = new Patient(1, "John", "Doe", "1234567891234", new Date(2000, 4, 5), 60, 1.70);

        when(patientRepository.createPatient(patient))
                .thenReturn(savedPatient);

        Patient result = patientService.createPatient(patient);

        assertEquals(savedPatient.getId(), result.getId());
        assertEquals(patient.getFirstName(), result.getFirstName());
        assertEquals(patient.getLastName(), result.getLastName());
        assertEquals(patient.getBirthdate(), result.getBirthdate());
        assertEquals(patient.getCNP(), result.getCNP());
        assertEquals(patient.getWeight(), result.getWeight());
        assertEquals(patient.getHeight(), patient.getHeight());
    }

    @Test
    public void testGetPatientByNameSuccess() {
        Patient patient = new Patient(1, "John", "Doe", "1234567891234", new Date(2000, 4, 5), 60, 1.70);

        when(patientRepository.getPatientByName("John", "Doe"))
                .thenReturn(Optional.of(patient));

        Patient result = patientService.getPatientByName("John", "Doe");
        assertEquals(patient.getId(), result.getId());
        assertEquals(patient.getFirstName(), result.getFirstName());
        assertEquals(patient.getLastName(), result.getLastName());
        assertEquals(patient.getBirthdate(), result.getBirthdate());
        assertEquals(patient.getCNP(), result.getCNP());
        assertEquals(patient.getWeight(), result.getWeight());
        assertEquals(patient.getHeight(), patient.getHeight());
    }

    @Test
    public void testGetPatientByNameFailure() {
        String firstName = "John";
        String lastName = "Doe";

        when(patientRepository.getPatientByName(firstName, lastName))
                .thenReturn(Optional.empty());

        PatientNotFoundException exception = assertThrows(PatientNotFoundException.class, () ->
                patientService.getPatientByName(firstName, lastName));

        assertEquals("Patient with name " + firstName + " "  + lastName + " does not exist", exception.getMessage());
    }
}
