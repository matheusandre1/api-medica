package com.example.api_med.dto;

import com.example.api_med.core.Adress;
import com.example.api_med.core.Specialty;

public record DoctorDto(String name,
                        String email,
                        String phone,
                        String crm,
                        Specialty specialty,
                        AdressDto adress) {

}
