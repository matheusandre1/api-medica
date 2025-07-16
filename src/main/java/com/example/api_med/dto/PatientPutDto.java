package com.example.api_med.dto;

import jakarta.validation.constraints.NotNull;

public record PatientPutDto(
        @NotNull
        Long id,
        String name,
        String phone,
        AdressDto adressDto)
{
}
