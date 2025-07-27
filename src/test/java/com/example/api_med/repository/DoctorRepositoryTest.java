package com.example.api_med.repository;

import com.example.api_med.core.Consultation;
import com.example.api_med.core.Doctor;
import com.example.api_med.core.Patient;
import com.example.api_med.core.Specialty;
import com.example.api_med.dto.AdressDto;
import com.example.api_med.dto.DoctorDto;
import com.example.api_med.dto.PatientDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deveria devolver um null quando um unico médico cadastrado não está disponivel na data")
    void chooseRandomDoctor() {

        var nextDate = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = createDoctor("Dr. João", "medico@gmail.com", "123456", Specialty.Cardiologia);
        var patient = createPatient("Paciente Teste", "patient@gmail.com", "12345678901");

        createConsultation( doctor, patient, nextDate);

        var doctorFree = doctorRepository.chooseRandomDoctor(Specialty.Cardiologia, nextDate);
        assertThat(doctorFree).isNull();
    }

    @Test
    @DisplayName("Deveria devolver médico quando estiver disponivel  na data")
    void chooseRandomDoctor2() {

        var nextDate = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = createDoctor("Dr. João", "medico@gmail.com", "123456", Specialty.Cardiologia);

        var doctorFree = doctorRepository.chooseRandomDoctor(Specialty.Cardiologia, nextDate);
        assertThat(doctorFree).isEqualTo(doctor);
    }

    private void createConsultation(Doctor doctor, Patient patient, LocalDateTime date) {

        entityManager.persist(new Consultation(null, doctor, patient, date, null));
    }

    private Doctor createDoctor(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(dateDoctor(name, email, crm, specialty));

        entityManager.persist(doctor);
        return doctor;
    }

    private Patient createPatient(String name, String email, String cpf)
    {
        var patient = new Patient(dataPatient(name,email, cpf));

        entityManager.persist(patient);
        return patient;
    }

    private DoctorDto dateDoctor(String name, String email,String crm, Specialty specialty) {
        return new DoctorDto(
                name,
                email,
                "61999999999",
                crm,
                specialty,
                dataAdress()
        );
    }

    private PatientDto dataPatient(String name, String email, String cpf)
    {
        return new PatientDto(
                name,
                email,
                "61999999999",
                cpf,
                dataAdress()
        );
    }


    private AdressDto dataAdress() {
        return new AdressDto(
                "rua xpto",
                "bairro xpto",
                "0000000",
                "SP",
                "São Paulo",
                "123",
                null
        );
    }
}