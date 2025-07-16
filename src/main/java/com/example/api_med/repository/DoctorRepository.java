package com.example.api_med.repository;

import com.example.api_med.core.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByAtivoTrue(Pageable pagination);
}
