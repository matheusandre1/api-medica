package com.example.api_med.services;

import com.example.api_med.core.Consultation;
import com.example.api_med.core.Doctor;
import com.example.api_med.dto.CancellationDateDto;
import com.example.api_med.dto.DataDetailsConsultation;
import com.example.api_med.dto.DataSchedulingDto;
import com.example.api_med.infra.exception.ValidationException;
import com.example.api_med.repository.ConsultationRepository;
import com.example.api_med.repository.DoctorRepository;
import com.example.api_med.repository.PatientRepository;
import com.example.api_med.validations.cancellation.ValidatorCancelationConsultation;
import com.example.api_med.validations.consultation.AppointmentSchedulingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulingAppointmentsService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<AppointmentSchedulingValidator> validators;

    @Autowired
    private List<ValidatorCancelationConsultation> validatorCancelationConsultations;

    public DataDetailsConsultation scheduling(DataSchedulingDto dataSchedulingDto)
    {
        if (!patientRepository.existsById(dataSchedulingDto.idPatient()))
        {
            throw new ValidationException("Id do Paciente informado não existe! ");
        }

        if (dataSchedulingDto.idDoctor() != null && !doctorRepository.existsById(dataSchedulingDto.idDoctor()))
        {
            throw new ValidationException("Id do Médico informado não existe! ");
        }

        validators.forEach(v-> v.validate(dataSchedulingDto));

        var patient = patientRepository.getReferenceById(dataSchedulingDto.idPatient());

        var doctor = chooseADoctor(dataSchedulingDto);

        if (doctor == null)
        {
            throw new ValidationException("Não existe médico disponivel nesse data!");
        }

        var consultation = new Consultation(null,doctor, patient,dataSchedulingDto.data(),null);

        consultationRepository.save(consultation);

        return new DataDetailsConsultation(consultation);
    }

    public void cancellation(CancellationDateDto cancellationDateDto)
    {
        if (!consultationRepository.existsById(cancellationDateDto.idConsultation()))
        {
            throw new ValidationException("Id da consulta informado não existe!");
        }

        validatorCancelationConsultations.forEach(v-> v.validate(cancellationDateDto));

        var consultation = consultationRepository.getReferenceById(cancellationDateDto.idConsultation());
        consultation.cancelar(cancellationDateDto.reasonForCancellation());
    }

    private Doctor chooseADoctor(DataSchedulingDto dataSchedulingDto)
    {
        if(dataSchedulingDto.idDoctor() != null)
        {
            return doctorRepository.getReferenceById(dataSchedulingDto.idDoctor());
        }

        if(dataSchedulingDto.specialty() == null)
        {
           throw  new ValidationException("Especialidade é obrigatória quando o médico não for escolhido.");
        }


        return doctorRepository.chooseRandomDoctor(dataSchedulingDto.specialty(), dataSchedulingDto.data());

    }
}
