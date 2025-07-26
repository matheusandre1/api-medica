package com.example.api_med.controller;

import com.example.api_med.dto.CancellationDateDto;
import com.example.api_med.dto.DataDetailsConsultation;
import com.example.api_med.dto.DataSchedulingDto;
import com.example.api_med.services.SchedulingAppointmentsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultationController {

    @Autowired
    private SchedulingAppointmentsService schedulingAppointmentsService;

    @PostMapping
    @Transactional
    public ResponseEntity ascheduling(@RequestBody @Valid DataSchedulingDto dataSchedulingDto)
    {

       var dto = schedulingAppointmentsService.scheduling(dataSchedulingDto);

        return ResponseEntity.ok(dto);

    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancellation(@RequestBody @Valid CancellationDateDto cancellationDateDto)
    {
        schedulingAppointmentsService.cancellation(cancellationDateDto);

        return ResponseEntity.noContent().build();
    }
}
