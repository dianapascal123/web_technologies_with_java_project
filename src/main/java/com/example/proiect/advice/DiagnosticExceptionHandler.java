package com.example.proiect.advice;

import com.example.proiect.exception.DiagnosticNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DiagnosticExceptionHandler {
    @ExceptionHandler(DiagnosticNotFoundException.class)
    public ResponseEntity<String> handleNotFoundDiagnostic(DiagnosticNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
