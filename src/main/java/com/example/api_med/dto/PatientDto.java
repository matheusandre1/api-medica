package com.example.api_med.dto;

public record PatientDto(String nome,
                         String email,
                         String phone,
                         String cpf,
                         AdressDto adress) {

}
