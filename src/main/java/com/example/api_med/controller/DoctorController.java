package com.example.api_med.controller;

import com.example.api_med.core.Adress;
import com.example.api_med.core.Doctor;
import com.example.api_med.dto.DoctorDto;
import com.example.api_med.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void cadastre(@RequestBody DoctorDto doctorDto) {

        doctorRepository.save(new Doctor(doctorDto));
        // Implement the logic to register a doctor
        // This method will handle POST requests to /doctors
        // You can add parameters and logic as needed
    }
}
