package com.example.proiect.controller;

import com.example.proiect.dto.PatientRequest;
import com.example.proiect.mapper.PatientMapper;
import com.example.proiect.model.Patient;
import com.example.proiect.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@Validated
@RequestMapping("/patients")
public class PatientController {
    private PatientService patientService;
    private PatientMapper patientMapper;

    public PatientController(PatientService patientService, PatientMapper patientMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@Valid @RequestBody PatientRequest patientRequest) {
        Patient patient = patientMapper.patientRequestToPatient(patientRequest);
        Patient createdPatient = patientService.createPatient(patient);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createdPatient);
    }
}
