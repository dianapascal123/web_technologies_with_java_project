package com.example.proiect.exception;

public class DiagnosticNotFoundException extends RuntimeException {
    public DiagnosticNotFoundException(String message) {
        super(message);
    }
}
