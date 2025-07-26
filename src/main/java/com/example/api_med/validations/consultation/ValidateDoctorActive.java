package com.example.api_med.validations.consultation;

import com.example.api_med.dto.DataSchedulingDto;
import com.example.api_med.infra.exception.ValidationException;
import com.example.api_med.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDoctorActive implements AppointmentSchedulingValidator{

    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(DataSchedulingDto dataSchedulingDto)
    {
        if(dataSchedulingDto.idDoctor() == null)
        {
            return;
        }

        var doctorisActive = doctorRepository.findDoctorAtivoById(dataSchedulingDto.idDoctor());

        if (!doctorisActive) {
            throw new ValidationException("Consulta não pode ser agendada com médico inativo.");
        }

    }
}
