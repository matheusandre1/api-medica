package com.example.api_med.dto;


import com.example.api_med.core.Patient;


public record PatientDtoResponse(Long id,
        String name, String email,
        String phone,
        String cpf, AdressDto adressDto) {

    public PatientDtoResponse(Patient patient)
    {
        this(patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getCpf(),
                patient.getAdress() != null ? new AdressDto(patient.getAdress()) : null);
    }
}
