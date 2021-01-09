package com.example.proiect.dto;

import com.example.proiect.model.Doctor;
import com.example.proiect.model.Patient;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class AppointmentRequest {
    @NotNull
    private Date date;

    @NotNull
    private Patient patient;

    @NotNull
    private Doctor doctor;

    public AppointmentRequest(@NotNull Date date, @NotNull Patient patient, @NotNull Doctor doctor) {
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
