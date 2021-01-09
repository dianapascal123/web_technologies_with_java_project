package com.example.proiect.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Treatment {
    private int id;
    private String medicine;
    private Diagnostic diagnostic;
    private Date startDate;
    private Date endDate;

    public Treatment(String medicine, Diagnostic diagnostic, Date startDate, Date endDate) {
        this.medicine = medicine;
        this.diagnostic = diagnostic;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Treatment() {
    }
}


