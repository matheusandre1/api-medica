package com.example.api_med.dto;


import com.example.api_med.core.Doctor;
import com.example.api_med.core.Specialty;

public record DoctorListDto(
        Long id,
        String name,
        String email,
        String crm,
        Specialty specialty
) {
    public DoctorListDto(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getSpecialty()
        );
    }
}

