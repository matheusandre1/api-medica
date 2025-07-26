package com.example.api_med.repository;

import com.example.api_med.core.Consultation;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {


    boolean existsByPatientIdAndDateBetween(Long idPatient,LocalDateTime start, LocalDateTime end);

    boolean existsByDoctorIdAndDateAndReasonForCancellationIsNull(Long idDoctor, LocalDateTime date);
}
