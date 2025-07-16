package com.example.api_med.core;

import com.example.api_med.dto.DoctorDto;
import com.example.api_med.dto.DoctorPutDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Entity(name = "Medico")
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    private String crm;
    @Embedded
    private Adress adress;
    private Boolean ativo;

    public Doctor(DoctorDto doctorDto) {
        this.ativo = true;
        this.name = doctorDto.name();
        this.email = doctorDto.email();
        this.phone = doctorDto.phone();
        this.crm = doctorDto.crm();
        this.specialty = doctorDto.specialty();
        this.adress = new Adress(doctorDto.adress());
    }


    public void put( DoctorPutDto doctorDto) {
        if(doctorDto.name() != null)
        {
        this.name = doctorDto.name();
        }

        if(doctorDto.phone() != null)
        {
            this.phone = doctorDto.phone();
        }
        if(doctorDto.adressDto() != null) {
            this.adress.updateInformation(doctorDto.adressDto());
        }

    }

    public void desativar()
    {
        this.ativo = false;
    }
}
