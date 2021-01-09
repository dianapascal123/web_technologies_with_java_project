package com.example.proiect.advice;

import com.example.proiect.exception.AppointmentNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppointmentExceptionHandler {
    @ExceptionHandler(AppointmentNotFound.class)
    public ResponseEntity<String> handleNotFoundAppointment(AppointmentNotFound e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
