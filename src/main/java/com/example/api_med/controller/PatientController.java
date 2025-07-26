package com.example.api_med.controller;

import com.example.api_med.core.Doctor;
import com.example.api_med.core.Patient;
import com.example.api_med.dto.*;
import com.example.api_med.repository.PatientRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastre(@RequestBody @Valid PatientDto patientDto, UriComponentsBuilder componentsBuilder)
    {
        var patient = new Patient(patientDto);
        patientRepository.save(new Patient(patientDto));

        var uri = componentsBuilder.path("/pacientes/{id}").buildAndExpand(patient.getId()).toUri();

        return  ResponseEntity.created(uri).body(new PatientDtoResponse(patient));
    }

     @GetMapping("/{id}")
    public ResponseEntity exibir(@PathVariable Long id)
    {
        var patient = patientRepository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDtoResponse(patient));
    }

    @GetMapping("/all")
    public Page<PatientListDto> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return patientRepository.findAllByAtivoTrue(pagination)
                .map(PatientListDto::new);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid PatientPutDto patientPutDto) {
        var patient = patientRepository.getReferenceById(patientPutDto.id());
        patient.put(patientPutDto);

        return ResponseEntity.ok(new PatientDtoResponse(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id)
    {
        var patient = patientRepository.getReferenceById(id);
        patient.desativar();

        return ResponseEntity.noContent().build();    }
}
