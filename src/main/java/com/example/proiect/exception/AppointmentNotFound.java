package com.example.proiect.exception;

public class AppointmentNotFound extends RuntimeException {
    public AppointmentNotFound(String message) {
        super(message);
    }
}
