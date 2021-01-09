package com.example.proiect.mapper;

import com.example.proiect.dto.DiagnosticRequest;
import com.example.proiect.model.Diagnostic;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticMapper {
    public Diagnostic diagnosticRequestToDiagnostic(DiagnosticRequest diagnosticRequest) {
        return new Diagnostic(diagnosticRequest.getName(), diagnosticRequest.getPatient(), diagnosticRequest.getStartDate(),
                diagnosticRequest.getEndDate());
    }
}
