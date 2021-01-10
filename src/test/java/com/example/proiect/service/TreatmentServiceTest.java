package com.example.proiect.service;

import com.example.proiect.model.Diagnostic;
import com.example.proiect.model.Patient;
import com.example.proiect.model.Treatment;
import com.example.proiect.repository.TreatmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TreatmentServiceTest {

    @Mock
    private TreatmentRepository treatmentRepository;

    @Mock
    private DiagnosticService diagnosticService;

    @InjectMocks
    private TreatmentService treatmentService;

    @Test
    public void testCreateTreatment() {
        Patient patient = new Patient();
        patient.setFirstName("Jane");
        patient.setLastName("Doe");

        Diagnostic diagnostic = new Diagnostic(1,"infection", patient, new Date(2020, 9, 8),
                new Date(2021, 1, 1));

        Treatment treatment = new Treatment("test", diagnostic, new Date(2020, 9, 8), new Date(2021, 1, 1));
        Treatment savedTreatment = new Treatment(1,"test", diagnostic, new Date(2020, 9, 8), new Date(2021, 1, 1));

        when(diagnosticService.getDiagnosticById(1))
                .thenReturn(diagnostic);

        when(treatmentRepository.createTreatment(treatment))
                .thenReturn(savedTreatment);

        Treatment result = treatmentService.createTreatment(treatment);

        assertEquals(savedTreatment.getId(), result.getId());
        assertEquals(treatment.getMedicine(), result.getMedicine());
        assertEquals(treatment.getDiagnostic(), result.getDiagnostic());
        assertEquals(treatment.getStartDate(), result.getStartDate());
        assertEquals(treatment.getEndDate(), result.getEndDate());
    }
}
