package com.example.proiect.service;

import com.example.proiect.model.Diagnostic;
import com.example.proiect.model.Treatment;
import com.example.proiect.repository.TreatmentRepository;
import org.springframework.stereotype.Service;


@Service
public class TreatmentService {
    private TreatmentRepository treatmentRepository;
    private DiagnosticService diagnosticService;

    public TreatmentService(TreatmentRepository treatmentRepository, DiagnosticService diagnosticService) {
        this.treatmentRepository = treatmentRepository;
        this.diagnosticService = diagnosticService;
    }

    public Treatment createTreatment(Treatment treatment) {
        Diagnostic diagnostic = diagnosticService.getDiagnosticById(treatment.getDiagnostic().getId());

        treatment.setDiagnostic(diagnostic);

        return treatmentRepository.createTreatment(treatment);
    }
}
