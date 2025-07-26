package com.example.api_med.dto;

import com.example.api_med.core.Specialty;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataSchedulingDto(Long idDoctor,
                                @NotNull
                                Long idPatient,
                                @NotNull
                                @Future
                                @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
                                LocalDateTime data,
                                Specialty specialty) {
}
