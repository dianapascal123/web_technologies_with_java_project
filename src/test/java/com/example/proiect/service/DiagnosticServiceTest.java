package com.example.proiect.service;

import com.example.proiect.exception.DiagnosticNotFoundException;
import com.example.proiect.model.Diagnostic;
import com.example.proiect.model.Patient;
import com.example.proiect.repository.DiagnosticRepository;
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
public class DiagnosticServiceTest {

    @Mock
    private DiagnosticRepository diagnosticRepository;

    @Mock
    private PatientService patientService;

    @InjectMocks
    private DiagnosticService diagnosticService;

    @Test
    public void testCreateDiagnostic() {
        Patient patient = new Patient();
        patient.setFirstName("Jane");
        patient.setLastName("Doe");

        Diagnostic diagnostic = new Diagnostic("infection", patient, new Date(2020, 9, 8),
                new Date(2021, 1, 1));
        Diagnostic savedDiagnostic = new Diagnostic(1, "infection", patient, new Date(2020, 9, 8),
                new Date(2021, 1, 1));

        when(patientService.getPatientByName(patient.getFirstName(),
                patient.getLastName()))
                .thenReturn(patient);

        when(diagnosticRepository.createDiagnostic(diagnostic))
                .thenReturn(savedDiagnostic);

        Diagnostic result = diagnosticService.createDiagnostic(diagnostic);

        assertEquals(savedDiagnostic.getId(), result.getId());
        assertEquals(diagnostic.getName(), result.getName());
        assertEquals(diagnostic.getPatient(), result.getPatient());
        assertEquals(diagnostic.getStartDate(), result.getStartDate());
        assertEquals(diagnostic.getEndDate(), result.getEndDate());
    }

    @Test
    public void testGetDiagnosticByIdSuccess() {
        Patient patient = new Patient();
        patient.setFirstName("Jane");
        patient.setLastName("Doe");
        Diagnostic diagnostic = new Diagnostic(1, "infection", patient, new Date(2020, 9, 8),
                new Date(2021, 1, 1));

        when(diagnosticRepository.getDiagnosticById(diagnostic.getId()))
                .thenReturn(Optional.of(diagnostic));

        Diagnostic result = diagnosticService.getDiagnosticById(diagnostic.getId());

        assertEquals(diagnostic.getId(), result.getId());
        assertEquals(diagnostic.getName(), result.getName());
        assertEquals(diagnostic.getStartDate(), result.getStartDate());
        assertEquals(diagnostic.getEndDate(), result.getEndDate());
    }

    @Test
    public void testGetDiagnosticByIdFailure() {
        int diagnosticId = 1;

        when(diagnosticRepository.getDiagnosticById(diagnosticId))
                .thenReturn(Optional.empty());

        DiagnosticNotFoundException exception = assertThrows(DiagnosticNotFoundException.class, () ->
                diagnosticService.getDiagnosticById(diagnosticId));

        assertEquals("Diagnostic with id " + diagnosticId + " not found", exception.getMessage());
    }
}
