package com.example.api_med.controller;

import com.example.api_med.dto.PatientDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    public void cadastre(@RequestBody PatientDto patientDto) {

    }
}
