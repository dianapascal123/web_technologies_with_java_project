package com.example.proiect.service;

import com.example.proiect.exception.AppointmentNotFound;
import com.example.proiect.model.Appointment;
import com.example.proiect.model.Doctor;
import com.example.proiect.model.Patient;
import com.example.proiect.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private DoctorService doctorService;

    @Mock
    private PatientService patientService;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    public void testCreateAppointment() {
        Doctor doctor = new Doctor();
        doctor.setFirstName("John");
        doctor.setLastName("Doe");

        Patient patient = new Patient();
        patient.setFirstName("Jane");
        patient.setLastName("Doe");

        Appointment appointment = new Appointment(new Date(2021, 7, 8, 15, 30, 0),
                patient, doctor);
        Appointment savedAppointment = new Appointment(1, new Date(2021, 7, 8, 15, 30, 0),
                patient, doctor);

        when(doctorService.getDoctorByName(appointment.getDoctor().getFirstName(),
                appointment.getDoctor().getLastName()))
                .thenReturn(doctor);

        when(patientService.getPatientByName(appointment.getPatient().getFirstName(),
                appointment.getPatient().getLastName()))
                .thenReturn(patient);

        when(appointmentRepository.createAppointment(appointment))
                .thenReturn(savedAppointment);

        Appointment result = appointmentService.createAppointment(appointment);

        assertEquals(savedAppointment.getId(), result.getId());
        assertEquals(appointment.getDate(), result.getDate());
        assertEquals(appointment.getPatient(), result.getPatient());
        assertEquals(appointment.getDoctor(), result.getDoctor());
    }

    @Test
    public void testUpdateByIdSuccess() {
        Appointment appointment = new Appointment(3, new Date(2021, 3, 5, 17, 30, 0));

        when(appointmentRepository.updateById(appointment))
                .thenReturn(true);
        appointmentService.updateById(appointment);

        verify(appointmentRepository).updateById(appointment);
    }

    @Test
    public void testUpdateByIdFailure() {
        Appointment appointment = new Appointment(3, new Date(2021, 3, 5, 17, 30, 0));

        when(appointmentRepository.updateById(appointment))
                .thenReturn(false);

        AppointmentNotFound exception = assertThrows(AppointmentNotFound.class, () ->
                appointmentService.updateById(appointment));

        assertEquals("Appointment with id " + appointment.getId() + " was not found", exception.getMessage());
    }

    @Test
    public void testDeleteByIdSuccess() {
        int appointmentId = 1;

        when(appointmentRepository.deleteById(appointmentId))
                .thenReturn(true);
        appointmentService.deleteById(appointmentId);

        verify(appointmentRepository).deleteById(appointmentId);
    }

    @Test
    public void testDeleteByIdFailure() {
        int appointmentId = 1;

        when(appointmentRepository.deleteById(appointmentId))
                .thenReturn(false);

        AppointmentNotFound exception = assertThrows(AppointmentNotFound.class, () ->
                appointmentService.deleteById(appointmentId));

        assertEquals("Appointment with id " + appointmentId+ " was not found", exception.getMessage());
    }
}
