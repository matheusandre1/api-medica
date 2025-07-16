package com.example.api_med.core;

import com.example.api_med.dto.DoctorPutDto;
import com.example.api_med.dto.PatientDto;
import com.example.api_med.dto.PatientPutDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    @Embedded
    private Adress adress;
    private Boolean ativo;

    public Patient(PatientDto patientDto) {
        this.ativo = true;
        this.name = patientDto.name();
        this.email = patientDto.email();
        this.phone = patientDto.phone();
        this.cpf = patientDto.cpf();
        this.adress = new Adress(patientDto.adress());
    }

    public void put(PatientPutDto patientPutDto) {
        if(patientPutDto.name() != null)
        {
            this.name = patientPutDto.name();
        }

        if(patientPutDto.phone() != null)
        {
            this.phone = patientPutDto.phone();
        }
        if(patientPutDto.adressDto() != null) {
            this.adress.updateInformation(patientPutDto.adressDto());
        }

    }

    public void desativar() {
        this.ativo = false;
    }
}
