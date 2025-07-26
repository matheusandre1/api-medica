package com.example.api_med.dto;

import com.example.api_med.core.ReasonForCancellation;
import jakarta.validation.constraints.NotNull;

public record CancellationDateDto(@NotNull Long idConsultation,
                                  @NotNull
                                  ReasonForCancellation reasonForCancellation) {
}
