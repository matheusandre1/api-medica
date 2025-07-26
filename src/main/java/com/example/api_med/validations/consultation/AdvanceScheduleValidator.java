package com.example.api_med.validations.consultation;

import com.example.api_med.dto.DataSchedulingDto;
import com.example.api_med.infra.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("AdvanceScheduleValidatorScheduling")
public class AdvanceScheduleValidator implements AppointmentSchedulingValidator {

    public void validate(DataSchedulingDto dataSchedulingDto)
    {
        var dateConsultation = dataSchedulingDto.data();
        var now = LocalDateTime.now();

        var diference = Duration.between(now, dateConsultation).toMinutes();

        if (diference < 30) {
            throw new ValidationException("Consulta deve ser agendada com antecedência de 30 minutos.");
        }
    }
}
