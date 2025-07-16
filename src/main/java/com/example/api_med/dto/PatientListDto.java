package com.example.api_med.dto;

import com.example.api_med.core.Patient;

public record PatientListDto(Long id,
                             String nome,
                             String email,
                             String cpf
){
    public PatientListDto(Patient patient) {
        this(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getCpf()
        );
    }
}