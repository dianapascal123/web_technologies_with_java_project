package com.example.proiect.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Patient {
    private int id;
    private String firstName;
    private String lastName;
    private String CNP;
    private Date birthdate;
    private double weight;
    private double height;

    public Patient(String firstName, String lastName, String CNP, Date birthdate, double weight, double height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.CNP = CNP;
        this.birthdate = birthdate;
        this.weight = weight;
        this.height = height;
    }

    public Patient() {
    }
}
