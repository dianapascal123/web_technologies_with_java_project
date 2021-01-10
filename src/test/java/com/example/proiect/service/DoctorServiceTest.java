package com.example.proiect.service;

import com.example.proiect.exception.DoctorNotFoundException;
import com.example.proiect.model.Doctor;
import com.example.proiect.model.Hospital;
import com.example.proiect.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    public void testGetDoctorByNameFailure() {
        String firstName = "John";
        String lastName = "Doe";

        when(doctorRepository.getDoctorByName(firstName, lastName))
                .thenReturn(Optional.empty());

        DoctorNotFoundException exception = assertThrows(DoctorNotFoundException.class, () ->
                doctorService.getDoctorByName(firstName, lastName));

        assertEquals("Doctor with name " + firstName + " "  + lastName + " does not exist", exception.getMessage());
    }

    @Test
    public void testGetAll() {
        Hospital hospital = new Hospital(1, "test", "test", "test");
        Doctor doctor = new Doctor(1,"John", "Doe", "specialization", hospital);
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);

        when(doctorRepository.getAll())
                .thenReturn(doctors);

        List<Doctor> results = doctorService.getAll();
        for(int i = 0; i < doctors.size(); i++) {
            Doctor d = doctors.get(i);
            Doctor result = results.get(i);
            assertEquals(d.getId(), result.getId());
            assertEquals(d.getFirstName(), result.getFirstName());
            assertEquals(d.getLastName(), result.getLastName());
            assertEquals(d.getSpecialization(), result.getSpecialization());
            assertEquals(d.getHospital(), result.getHospital());
        }
    }
}
