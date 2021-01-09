package com.example.proiect.mapper;

import com.example.proiect.dto.AppointmentRequest;
import com.example.proiect.dto.AppointmentUpdate;
import com.example.proiect.model.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public Appointment appointmentRequestToAppointment(AppointmentRequest appointmentRequest) {
        return new Appointment(appointmentRequest.getDate(), appointmentRequest.getPatient(), appointmentRequest.getDoctor());
    }

    public Appointment appointmentUpdateToAppointment(AppointmentUpdate appointmentUpdate){
        return new Appointment(appointmentUpdate.getId(), appointmentUpdate.getDate());
    }
}
