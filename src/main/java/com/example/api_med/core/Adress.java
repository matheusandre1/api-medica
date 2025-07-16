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

    public void updateInformation(AdressDto adressDto) {
        if(adressDto.logradouro() != null){
        this.logradouro = adressDto.logradouro();
        }
        if(adressDto.bairro() != null)
        {
        this.bairro = adressDto.bairro();
        }
        if(adressDto.cep() != null)
        {
        this.cep = adressDto.cep();
        }
        if(adressDto.uf() != null) {
            this.uf = adressDto.uf();
        }
        if(adressDto.cidade() != null){
        this.cidade = adressDto.cidade();
        }
        if(adressDto.numero() != null){
        this.numero = adressDto.numero();
        }
        if(adressDto.complemento() != null) {
            this.complemento = adressDto.complemento();
        }
    }
}
