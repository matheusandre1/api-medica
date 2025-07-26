package com.example.api_med.validations.consultation;

import com.example.api_med.dto.DataSchedulingDto;
import com.example.api_med.infra.exception.ValidationException;
import com.example.api_med.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDoctorWithAnotherAppointmentAtTheSameTime implements AppointmentSchedulingValidator {

    @Autowired
    private ConsultationRepository consultationRepository;

    public void validate(DataSchedulingDto dataSchedulingDto)
    {
        var doctorHasAnotherAppointmentScheduledAtTheSameTime = consultationRepository.existsByDoctorIdAndDateAndReasonForCancellationIsNull(
                dataSchedulingDto.idDoctor(),
                dataSchedulingDto.data()
        );

        if (doctorHasAnotherAppointmentScheduledAtTheSameTime) {
            throw new ValidationException("O médico já possui um agendamento nesse horário.");
        }
    }
}
