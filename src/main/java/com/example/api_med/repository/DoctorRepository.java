package com.example.api_med.repository;

import com.example.api_med.core.Doctor;
import com.example.api_med.core.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByAtivoTrue(Pageable pagination);


    @Query("""
            select d from Doctor d
            where d.ativo = true
            and d.specialty = :specialty
            and
            d.id not in (
                select c.doctor.id from Consultation c
                where c.date = :data
                and c.reasonForCancellation is null
            )
            order by random()
            limit 1
            """)
    Doctor chooseRandomDoctor(Specialty specialty,LocalDateTime data);


    @Query("""
           select m.ativo from Doctor m
           where 
           m.id = :id
           """)
    Boolean findDoctorAtivoById(Long id);

}
