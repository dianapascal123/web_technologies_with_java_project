package com.example.proiect.mapper;

import com.example.proiect.dto.DoctorRequest;
import com.example.proiect.model.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    public Doctor DoctorRequestToDoctor(DoctorRequest doctorRequest) {
        return new Doctor(doctorRequest.getFirstName(), doctorRequest.getLastName(),
                doctorRequest.getSpecialization(), doctorRequest.getHospital());
    }
}
