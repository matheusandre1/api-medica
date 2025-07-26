package com.example.api_med.validations.consultation;

import com.example.api_med.dto.DataSchedulingDto;
import com.example.api_med.infra.exception.ValidationException;
import com.example.api_med.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientNoOtherDateOnTheDay implements AppointmentSchedulingValidator{

    @Autowired
    private ConsultationRepository consultationRepository;

    public void validate(DataSchedulingDto dataSchedulingDto)
    {
        var primaryHours = dataSchedulingDto.data().withHour(7);
        var lastHour = dataSchedulingDto.data().withHour(18);
        var patientHaveAnotherAppointmentOnTheDay = consultationRepository.existsByPatientIdAndDateBetween(dataSchedulingDto.idPatient(), primaryHours, lastHour);

        if(patientHaveAnotherAppointmentOnTheDay)
        {
            throw  new ValidationException("Paciente já possui uma consulta agendada nesse dia");
        }
    }
}
