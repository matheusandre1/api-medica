package com.example.api_med.core;

import com.example.api_med.dto.AdressDto;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Adress {
    private String logradouro;
    private String bairro;
    private String cep;
    private String uf;
    private String cidade;
    private String numero;
    private String complemento;

    public Adress(AdressDto adressDto) {
        this.logradouro = adressDto.logradouro();
        this.bairro = adressDto.bairro();
        this.cep = adressDto.cep();
        this.uf = adressDto.uf();
        this.cidade = adressDto.cidade();
        this.numero = adressDto.numero();
        this.complemento = adressDto.complemento();
    }
}
