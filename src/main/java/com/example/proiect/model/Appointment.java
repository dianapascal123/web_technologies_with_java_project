package com.example.proiect.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Appointment {
    private int id;
    private Date date;
    private Patient patient;
    private Doctor doctor;

    public Appointment(Date date, Patient patient, Doctor doctor) {
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
    }

    public Appointment(int id, Date date) {
        this.id = id;
        this.date = date;
    }
}
