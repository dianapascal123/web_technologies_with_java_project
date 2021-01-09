package com.example.proiect.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Doctor {
    private int id;
    private String firstName;
    private String lastName;
    private String specialization;
    private Hospital hospital;

    public Doctor(String firstName, String lastName, String specialization, Hospital hospital) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.hospital = hospital;
    }

    public Doctor(int id, String firstName, String lastName, String specialization, Hospital hospital) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.hospital = hospital;
    }

    public Doctor() {
    }
}
