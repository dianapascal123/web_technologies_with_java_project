package com.example.proiect.service;

import com.example.proiect.exception.AppointmentNotFound;
import com.example.proiect.model.Appointment;
import com.example.proiect.model.Doctor;
import com.example.proiect.model.Patient;
import com.example.proiect.repository.AppointmentRepository;
import org.springframework.stereotype.Service;


@Service
public class AppointmentService {
    private AppointmentRepository appointmentRepository;
    private DoctorService doctorService;
    private PatientService patientService;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorService doctorService, PatientService patientService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    public Appointment createAppointment(Appointment appointment) {
        Doctor doctor = doctorService.getDoctorByName(appointment.getDoctor().getFirstName(), appointment.getDoctor().getLastName());
        appointment.setDoctor(doctor);

        Patient patient = patientService.getPatientByName(appointment.getPatient().getFirstName(), appointment.getPatient().getLastName());
        appointment.setPatient(patient);


        return appointmentRepository.createAppointment(appointment);
    }

    public void updateById(Appointment appointment) {
        if(!appointmentRepository.updateById(appointment)) {
            throw new AppointmentNotFound("Appointment with id " + appointment.getId() + " was not found");
        }
    }

    public void deleteById(int id) {
        if(!appointmentRepository.deleteById(id)) {
            throw new AppointmentNotFound("Appointment with id " + id + " was not found");
        }
    }
}
