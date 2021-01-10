package com.example.proiect.service;

import com.example.proiect.exception.HospitalNotFoundException;
import com.example.proiect.model.Hospital;
import com.example.proiect.repository.HospitalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HospitalServiceTest {

    @Mock
    private HospitalRepository hospitalRepository;

    @InjectMocks
    private HospitalService hospitalService;

    @Test
    public void testGetHospitalByNameSuccess() {
        Hospital hospital = new Hospital(1, "test", "test", "test");
        when(hospitalRepository.getHospitalByName("test"))
                .thenReturn(Optional.of(hospital));

        Hospital result = hospitalService.getHospitalByName("test");

        assertEquals(hospital.getId(), result.getId());
        assertEquals(hospital.getName(), result.getName());
        assertEquals(hospital.getCity(), result.getCity());
        assertEquals(hospital.getAddress(), result.getAddress());
    }

    @Test
    public void testGetHospitalByNameFailure() {
        String name = "test";
        when(hospitalRepository.getHospitalByName(name))
                .thenReturn(Optional.empty());

        HospitalNotFoundException exception = assertThrows(HospitalNotFoundException.class, () ->
                hospitalService.getHospitalByName(name));

        assertEquals("Hospital with name " + name + " not found", exception.getMessage());
    }
}
