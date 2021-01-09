package com.example.proiect.service;

import com.example.proiect.exception.DoctorNotFoundException;
import com.example.proiect.model.Doctor;
import com.example.proiect.model.Hospital;
import com.example.proiect.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private DoctorRepository doctorRepository;
    private HospitalService hospitalService;

    public DoctorService(DoctorRepository doctorRepository, HospitalService hospitalService) {
        this.doctorRepository = doctorRepository;
        this.hospitalService = hospitalService;
    }

    public Doctor createDoctor(Doctor doctor) {
       Hospital hospital = hospitalService.getHospitalByName(doctor.getHospital().getName());
       doctor.setHospital(hospital);
        return doctorRepository.createDoctor(doctor);
    }

    public Doctor getDoctorByName(String firstName, String lastName) {
        Optional<Doctor> doctor = doctorRepository.getDoctorByName(firstName, lastName);
        if (doctor.isPresent()) {
            return doctor.get();
        }
        else {
            throw new DoctorNotFoundException("Doctor with name " + firstName + " "  + lastName + " does not exist");
        }
    }

    public List<Doctor> getAll() {
        return doctorRepository.getAll();
    }
}
