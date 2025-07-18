package com.example.api_med.dto;

import com.example.api_med.core.Adress;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AdressDto(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String uf,
        @NotBlank
        String cidade,
        String numero,
        String complemento) {

        public AdressDto(Adress adress) {
                this(
                        adress.getLogradouro(),
                        adress.getBairro(),
                        adress.getCep(),
                        adress.getUf(),
                        adress.getCidade(),
                        adress.getNumero(),
                        adress.getComplemento()
                );
        }
}
