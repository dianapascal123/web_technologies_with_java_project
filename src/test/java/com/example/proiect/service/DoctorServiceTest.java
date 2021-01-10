package com.example.proiect.service;

import com.example.proiect.model.Doctor;
import com.example.proiect.model.Hospital;
import com.example.proiect.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private HospitalService hospitalService;

    @InjectMocks
    private DoctorService doctorService;

    @Test
    public void testCreateDoctor() {
        Hospital hospital = new Hospital(1, "test", "test", "test");
        Doctor doctor = new Doctor("John", "Doe", "specialization", hospital);
        Doctor savedDoctor = new Doctor(1,"John", "Doe", "specialization", hospital);

        when(hospitalService.getHospitalByName(doctor.getHospital().getName()))
                .thenReturn(hospital);

        when(doctorRepository.createDoctor(doctor))
                .thenReturn(savedDoctor);

        Doctor result = doctorService.createDoctor(doctor);

        assertEquals(savedDoctor.getId(), result.getId());
        assertEquals(doctor.getFirstName(), result.getFirstName());
        assertEquals(doctor.getLastName(), result.getLastName());
        assertEquals(doctor.getSpecialization(), result.getSpecialization());
        assertEquals(doctor.getHospital(), result.getHospital());
    }

    @Test
    public void testGetDoctorByNameSuccess() {
        Hospital hospital = new Hospital(1, "test", "test", "test");
        Doctor doctor = new Doctor(1,"John", "Doe", "specialization", hospital);
        when(doctorRepository.getDoctorByName("John", "Doe"))
                .thenReturn(Optional.of(doctor));

        Doctor result = doctorService.getDoctorByName("John", "Doe");

        assertEquals(doctor.getId(), result.getId());
        assertEquals(doctor.getFirstName(), result.getFirstName());
        assertEquals(doctor.getLastName(), result.getLastName());
        assertEquals(doctor.getSpecialization(), result.getSpecialization());
        assertEquals(doctor.getHospital(), result.getHospital());
    }


}
