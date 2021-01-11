package com.example.proiect.controller;

import com.example.proiect.dto.TreatmentRequest;
import com.example.proiect.mapper.TreatmentMapper;
import com.example.proiect.model.Treatment;
import com.example.proiect.service.TreatmentService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Validated
@RequestMapping("/treatments")
@Api(value = "/treatments", tags = "Treatments")

public class TreatmentController {
    TreatmentService treatmentService;
    TreatmentMapper treatmentMapper;

    public TreatmentController(TreatmentService treatmentService, TreatmentMapper treatmentMapper) {
        this.treatmentService = treatmentService;
        this.treatmentMapper = treatmentMapper;
    }

    @PostMapping
    public ResponseEntity<Treatment> createTreatment(@Valid @RequestBody TreatmentRequest treatmentRequest) {
        Treatment treatment = treatmentMapper.treatmentRequestToTreatment(treatmentRequest);

        Treatment createdTreatment = treatmentService.createTreatment(treatment);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createdTreatment);
    }
}
