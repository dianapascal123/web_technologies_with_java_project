package com.example.proiect.mapper;

import com.example.proiect.dto.PatientRequest;
import com.example.proiect.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {
    public Patient patientRequestToPatient(PatientRequest patientRequest) {
        return new Patient(patientRequest.getFirstName(), patientRequest.getLastName(), patientRequest.getCNP(),
                patientRequest.getBirthdate(), patientRequest.getWeight(), patientRequest.getHeight());
    }
}
