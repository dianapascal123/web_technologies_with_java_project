package com.example.proiect.dto;

import com.example.proiect.model.Hospital;

import javax.validation.constraints.NotNull;

public class DoctorRequest {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String specialization;

    @NotNull
    private Hospital hospital;

    public DoctorRequest(@NotNull String firstName, @NotNull String lastName, @NotNull String specialization, @NotNull Hospital hospital) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.hospital = hospital;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
