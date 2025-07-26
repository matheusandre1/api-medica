package com.example.api_med.validations.consultation;

import com.example.api_med.dto.DataSchedulingDto;
import com.example.api_med.infra.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidatorOperatingHours implements  AppointmentSchedulingValidator{

    public void validate(DataSchedulingDto dataSchedulingDto)
    {
        var dateConsultation = dataSchedulingDto.data();

        var sunday = dateConsultation.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        var beforeOpening = dateConsultation.getHour() < 7;
        var afterClosing = dateConsultation.getHour() > 18;

        if (sunday || beforeOpening || afterClosing) {
            throw new ValidationException("Consulta fora do funcionamento da clinica. ");
        }
    }
}
