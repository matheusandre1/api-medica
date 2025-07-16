package com.example.api_med.controller;

import com.example.api_med.core.Patient;
import com.example.api_med.dto.PatientDto;
import com.example.api_med.repository.PatientRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public void cadastre(@RequestBody @Valid PatientDto patientDto) {
        patientRepository.save(new Patient(patientDto));
    }
}
