package com.example.api_med.dto;

public record AdressDto(String logradouro,
                        String bairro,
                        String cep,
                        String uf,
                        String cidade,
                        String numero,
                        String complemento) {
}
