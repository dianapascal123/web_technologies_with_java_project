package com.example.proiect.controller;

import com.example.proiect.dto.DoctorRequest;
import com.example.proiect.mapper.DoctorMapper;
import com.example.proiect.model.Doctor;
import com.example.proiect.service.DoctorService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping("/doctors")
@Api(value = "/doctors", tags = "Doctors")

public class DoctorController {
    private DoctorService doctorService;
    private DoctorMapper doctorMapper;

    public DoctorController(DoctorService doctorService, DoctorMapper doctorMapper) {
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@Valid @RequestBody DoctorRequest doctorRequest) {
        Doctor doctor = doctorMapper.DoctorRequestToDoctor(doctorRequest);
        Doctor createdDoctor = doctorService.createDoctor(doctor);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createdDoctor);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Doctor>> getAll() {
        return ResponseEntity.ok().body(doctorService.getAll());
    }
}
