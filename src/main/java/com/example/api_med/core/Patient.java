package com.example.api_med.core;

import com.example.api_med.dto.PatientDto;
import jakarta.persistence.*;
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

    public Patient(PatientDto patientDto) {
        this.name = patientDto.name();
        this.email = patientDto.email();
        this.phone = patientDto.phone();
        this.cpf = patientDto.cpf();
        this.adress = new Adress(patientDto.adress());
    }
}
