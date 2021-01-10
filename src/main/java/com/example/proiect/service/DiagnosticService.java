package com.example.proiect.service;

import com.example.proiect.exception.DiagnosticNotFoundException;
import com.example.proiect.model.Diagnostic;
import com.example.proiect.model.Patient;
import com.example.proiect.repository.DiagnosticRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiagnosticService {
    private DiagnosticRepository diagnosticRepository;
    private PatientService patientService;

    public DiagnosticService(DiagnosticRepository diagnosticRepository, PatientService patientService) {
        this.diagnosticRepository = diagnosticRepository;
        this.patientService = patientService;
    }

    public Diagnostic createDiagnostic(Diagnostic diagnostic) {
        Patient patient = patientService.getPatientByName(diagnostic.getPatient().getFirstName(),
                diagnostic.getPatient().getLastName());

        diagnostic.setPatient(patient);

        return diagnosticRepository.createDiagnostic(diagnostic);
    }

    public Diagnostic getDiagnosticById(int id) {
        Optional<Diagnostic> diagnostic = diagnosticRepository.getDiagnosticById(id);
        if(!diagnostic.isPresent()) {
            throw new DiagnosticNotFoundException("Diagnostic with id " + id + " not found");
        }
        else {
            return diagnostic.get();
        }
    }
}
