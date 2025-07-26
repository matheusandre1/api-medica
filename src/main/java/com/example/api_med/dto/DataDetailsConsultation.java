package com.example.api_med.dto;

import com.example.api_med.core.Consultation;

import java.time.LocalDateTime;

public record DataDetailsConsultation(Long id,
                                      Long idDoctor,
                                      Long idPatient,
                                      LocalDateTime date) {
    public DataDetailsConsultation(Consultation consultation) {
        this(consultation.getId(),
             consultation.getDoctor().getId(),
             consultation.getPatient().getId(),
             consultation.getDate());
    }
}
