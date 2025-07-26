package com.example.api_med.validations.cancellation;

import com.example.api_med.dto.CancellationDateDto;
import com.example.api_med.infra.exception.ValidationException;
import com.example.api_med.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("AdvanceScheduleValidatorCancellation")
public class AdvanceScheduleValidator implements ValidatorCancelationConsultation{

    @Autowired
    private ConsultationRepository consultationRepository;
    @Override
    public void validate(CancellationDateDto cancellationDateDto) {

        var consultation = consultationRepository.getReferenceById(cancellationDateDto.idConsultation());
        var now = LocalDateTime.now();

        var differenceinHours = java.time.Duration.between(now, consultation.getDate()).toHours();

        if (differenceinHours < 24) {
            throw new ValidationException("A consulta só pode ser cancelada com pelo menos 24 horas de antecedência.");

        }
    }
}
