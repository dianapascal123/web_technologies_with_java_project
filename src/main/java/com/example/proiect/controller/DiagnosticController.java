package com.example.proiect.controller;

import com.example.proiect.dto.DiagnosticRequest;
import com.example.proiect.mapper.DiagnosticMapper;
import com.example.proiect.model.Diagnostic;
import com.example.proiect.service.DiagnosticService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/diagnostics")
@Api(value = "/diagnostics", tags = "Diagnostics")

public class DiagnosticController {
    private DiagnosticService diagnosticService;
    private DiagnosticMapper diagnosticMapper;

    public DiagnosticController(DiagnosticService diagnosticService, DiagnosticMapper diagnosticMapper) {
        this.diagnosticService = diagnosticService;
        this.diagnosticMapper = diagnosticMapper;
    }

    @PostMapping
    public ResponseEntity<Diagnostic> createDiagnostic(@Valid @RequestBody DiagnosticRequest diagnosticRequest) {
        Diagnostic diagnostic = diagnosticMapper.diagnosticRequestToDiagnostic(diagnosticRequest);

        Diagnostic createdDiagnostic = diagnosticService.createDiagnostic(diagnostic);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createdDiagnostic);
    }

    @GetMapping("/{id}")
    public ResponseEntity getDiagnosticById(@PathVariable("id") int id) {
       Diagnostic diagnostic = diagnosticService.getDiagnosticById(id);

       return ResponseEntity.ok().body(diagnostic);

    }
}
