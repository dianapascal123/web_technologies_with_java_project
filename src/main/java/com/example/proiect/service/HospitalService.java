package com.example.proiect.service;

import com.example.proiect.exception.HospitalNotFoundException;
import com.example.proiect.model.Hospital;
import com.example.proiect.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HospitalService {
    private HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Hospital getHospitalByName(String name) {
        Optional<Hospital> hospital = hospitalRepository.getHospitalByName(name);
        if(!hospital.isPresent()) {
            throw new HospitalNotFoundException("Hospital with name " + name + " not found");
        }
        else {
            return hospital.get();
        }
    }
}
