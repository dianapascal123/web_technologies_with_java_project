package com.example.proiect.advice;

import com.example.proiect.exception.DoctorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DoctorExceptionHandler {

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<String> handleNotFoundDoctor(DoctorNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
