package com.example.proiect.dto;

import com.example.proiect.model.Diagnostic;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class TreatmentRequest {

    @NotNull
    private String medicine;

    @NotNull
    private Diagnostic diagnostic;

    private Date startDate;
    private Date endDate;

    public TreatmentRequest(@NotNull String medicine, @NotNull Diagnostic diagnostic, Date startDate, Date endDate) {
        this.medicine = medicine;
        this.diagnostic = diagnostic;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public Diagnostic getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(Diagnostic diagnostic) {
        this.diagnostic = diagnostic;
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
