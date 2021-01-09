package com.example.proiect.advice;

import com.example.proiect.exception.HospitalNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HospitalExceptionHandler {
    @ExceptionHandler(HospitalNotFoundException.class)
    public ResponseEntity<String> handleNotFoundHospital(HospitalNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
