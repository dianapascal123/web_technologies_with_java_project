package com.example.proiect.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

import static com.example.proiect.model.Pattern.CNP_PATTERN;

public class PatientRequest {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Pattern(regexp = CNP_PATTERN)
    private String CNP;

    @NotNull
    private Date birthdate;

    private double weight;
    private double height;

    public PatientRequest(@NotNull String firstName, @NotNull String lastName, @Pattern(regexp = CNP_PATTERN) String CNP, @NotNull Date birthdate, double weight, double height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.CNP = CNP;
        this.birthdate = birthdate;
        this.weight = weight;
        this.height = height;
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

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
