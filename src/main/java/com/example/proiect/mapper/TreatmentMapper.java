package com.example.proiect.mapper;

import com.example.proiect.dto.TreatmentRequest;
import com.example.proiect.model.Treatment;
import org.springframework.stereotype.Component;

@Component
public class TreatmentMapper {
    public Treatment treatmentRequestToTreatment(TreatmentRequest treatmentRequest) {
        return new Treatment(treatmentRequest.getMedicine(), treatmentRequest.getDiagnostic(),
                treatmentRequest.getStartDate(), treatmentRequest.getEndDate());
    }
}
