package com.example.api_med.validations.consultation;

import com.example.api_med.dto.DataSchedulingDto;
import com.example.api_med.infra.exception.ValidationException;
import com.example.api_med.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientActive implements AppointmentSchedulingValidator{

    @Autowired
    private PatientRepository patientRepository;

    public void validate(DataSchedulingDto dataSchedulingDto)
    {
        var patientActive = patientRepository.findAtivoById(dataSchedulingDto.idPatient());

        if (!patientActive) {
            throw new ValidationException("Consulta não pode ser agendada com paciente excluido.");
        }
    }
}
