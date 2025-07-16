package com.example.api_med.core;

import com.example.api_med.dto.DoctorDto;
import jakarta.persistence.*;
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

    public Doctor(DoctorDto doctorDto) {
        this.name = doctorDto.name();
        this.email = doctorDto.email();
        this.phone = doctorDto.phone();
        this.crm = doctorDto.crm();
        this.specialty = doctorDto.specialty();
        this.adress = new Adress(doctorDto.adress());
    }


}
