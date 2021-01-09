package com.example.proiect.dto;

import com.example.proiect.model.Patient;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class DiagnosticRequest {

    @NotNull
    private String name;

    @NotNull
    private Patient patient;

    private Date startDate;
    private Date endDate;

    public DiagnosticRequest(@NotNull String name, @NotNull Patient patient, Date startDate, Date endDate) {
        this.name = name;
        this.patient = patient;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
