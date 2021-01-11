package com.example.proiect.controller;

import com.example.proiect.dto.AppointmentRequest;
import com.example.proiect.dto.AppointmentUpdate;
import com.example.proiect.mapper.AppointmentMapper;
import com.example.proiect.model.Appointment;
import com.example.proiect.service.AppointmentService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Validated
@RequestMapping("/appointments")
@Api(value = "/appointments", tags = "Appointments")
public class AppointmentController {
    private AppointmentService appointmentService;
    private AppointmentMapper appointmentMapper;

    public AppointmentController(AppointmentService appointmentService, AppointmentMapper appointmentMapper) {
        this.appointmentService = appointmentService;
        this.appointmentMapper = appointmentMapper;
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody @Valid AppointmentRequest appointmentRequest) {
        Appointment appointment = appointmentMapper.appointmentRequestToAppointment(appointmentRequest);
        Appointment createdAppointment = appointmentService.createAppointment(appointment);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createdAppointment);
    }

    @PutMapping
    public ResponseEntity<String> updateAppointmentById(@Valid @RequestBody AppointmentUpdate appointmentUpdate) {
        Appointment appointment = appointmentMapper.appointmentUpdateToAppointment(appointmentUpdate);
        appointmentService.updateById(appointment);
        return ResponseEntity.status(HttpStatus.OK).body("Appointment was updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAppointmentById(@PathVariable("id") int id) {
       appointmentService.deleteById(id);
       return ResponseEntity.status(HttpStatus.OK).body("Appointment was deleted");
    }
}
