package com.example.api_med.controller;

import com.example.api_med.core.Patient;
import com.example.api_med.dto.*;
import com.example.api_med.repository.PatientRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public void cadastre(@RequestBody @Valid PatientDto patientDto) {
        patientRepository.save(new Patient(patientDto));
    }


    @GetMapping("/all")
    public Page<PatientListDto> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return patientRepository.findAllByAtivoTrue(pagination)
                .map(PatientListDto::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid PatientPutDto patientPutDto) {
        var patient = patientRepository.getReferenceById(patientPutDto.id());
        patient.put(patientPutDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id)
    {
        var patient = patientRepository.getReferenceById(id);
        patient.desativar();
    }
}
