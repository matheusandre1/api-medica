package com.example.api_med.controller;


import com.example.api_med.core.Doctor;
import com.example.api_med.dto.*;
import com.example.api_med.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Comparator;
import java.util.List;


@RestController
@RequestMapping("/medicos")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastre(@RequestBody @Valid DoctorDto doctorDto, UriComponentsBuilder componentsBuilder) {
       var doctor = new Doctor(doctorDto);
       doctorRepository.save(doctor);

       var uri = componentsBuilder.path("/medicos/{id}").buildAndExpand(doctor.getId()).toUri();

       return  ResponseEntity.created(uri).body(new DoctorDtoResponse(doctor));

    }

    @GetMapping("/{id}")
    public ResponseEntity exibir(@PathVariable Long id)
    {
            var doctor = doctorRepository.getReferenceById(id);
            return ResponseEntity.ok(new DoctorDtoResponse(doctor));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DoctorListDto>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        var page = doctorRepository.findAllByAtivoTrue(pagination)
                .map(DoctorListDto::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DoctorPutDto doctorDto) {
        var doctor = doctorRepository.getReferenceById(doctorDto.id());
        doctor.put(doctorDto);

        return ResponseEntity.ok(new DoctorDtoResponse(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id)
    {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.desativar();

        return ResponseEntity.noContent().build();
    }


}
