package com.example.proiect.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Diagnostic {
    private int id;
    private String name;
    private Patient patient;
    private Date startDate;
    private Date endDate;

    public Diagnostic(String name, Patient patient, Date startDate, Date endDate) {
        this.name = name;
        this.patient = patient;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Diagnostic() {
    }
}
