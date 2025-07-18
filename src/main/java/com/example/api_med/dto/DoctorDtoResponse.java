package com.example.api_med.dto;


import com.example.api_med.core.Doctor;
import com.example.api_med.core.Specialty;

public record DoctorDtoResponse(Long id,
                                String name,
                                String email,
                                String crm,
                                String phone,
                                Specialty specialty,
                                AdressDto adressDto){

    public DoctorDtoResponse(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getPhone(),
                doctor.getSpecialty(),
                doctor.getAdress() != null ? new AdressDto(doctor.getAdress()) : null
        );
    }
}
