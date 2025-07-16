package com.example.api_med.dto;

import jakarta.validation.constraints.NotNull;

public record DoctorPutDto(

        @NotNull
        Long id,
        String name,
        String phone,
        AdressDto adressDto)
{
}
