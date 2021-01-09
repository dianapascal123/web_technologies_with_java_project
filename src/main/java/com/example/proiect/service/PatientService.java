package com.example.proiect.service;

import com.example.proiect.exception.DoctorNotFoundException;
import com.example.proiect.exception.PatientNotFoundException;
import com.example.proiect.model.Patient;
import com.example.proiect.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.createPatient(patient);
    }

    public Patient getPatientByName(String firstName, String lastName) {
        Optional<Patient> patient = patientRepository.getPatientByName(firstName, lastName);
        if (patient.isPresent()) {
            return patient.get();
        }
        else {
            throw new PatientNotFoundException("Patient with name " + firstName + " "  + lastName + " does not exist");
        }
    }
}
