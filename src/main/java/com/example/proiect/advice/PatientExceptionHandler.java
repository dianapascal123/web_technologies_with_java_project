package com.example.proiect.advice;

import com.example.proiect.exception.PatientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PatientExceptionHandler {
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<String> handleNotFoundPatient(PatientNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
