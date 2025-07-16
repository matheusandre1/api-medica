package com.example.api_med.controller;


import com.example.api_med.core.Doctor;
import com.example.api_med.dto.DoctorDto;
import com.example.api_med.dto.DoctorListDto;
import com.example.api_med.dto.DoctorPutDto;
import com.example.api_med.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;


@RestController
@RequestMapping("/medicos")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void cadastre(@RequestBody @Valid DoctorDto doctorDto) {
        doctorRepository.save(new Doctor(doctorDto));
    }

    @GetMapping("/all")
    public Page<DoctorListDto> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return doctorRepository.findAllByAtivoTrue(pagination)
                .map(DoctorListDto::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DoctorPutDto doctorDto) {
        var doctor = doctorRepository.getReferenceById(doctorDto.id());
        doctor.put(doctorDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id)
    {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.desativar();
    }


}
