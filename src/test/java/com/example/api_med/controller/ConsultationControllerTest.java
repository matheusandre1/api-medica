package com.example.api_med.controller;

import com.example.api_med.core.Specialty;
import com.example.api_med.dto.DataDetailsConsultation;
import com.example.api_med.dto.DataSchedulingDto;
import com.example.api_med.services.SchedulingAppointmentsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LOCAL_DATE_TIME;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DataSchedulingDto> dataSchedulingDtojson;

    @Autowired
    private JacksonTester<DataDetailsConsultation> dataDetailsConsultationjson;

    @MockitoBean
    private SchedulingAppointmentsService schedulingAppointmentsService;

    @Test
    @DisplayName("Deveria devolver código 400 quando informações estiverem inválidas")
    @WithMockUser
    void scheduling() throws Exception {

       var response = mockMvc.perform(post("/consultas"))
               .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código 200 quando informações estiverem válidas")
    @WithMockUser
    void scheduling2() throws Exception {

        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.Cardiologia;

        var dataDetails = new DataDetailsConsultation(null, 2L, 1L, date);
        when(schedulingAppointmentsService.scheduling(any()))
                .thenReturn(dataDetails);

        var response = mockMvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dataSchedulingDtojson.write
                        (new DataSchedulingDto(1L, 2L, date, specialty))
                        .getJson()))

                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var dataDetailsConsultation = dataDetailsConsultationjson.write
                (dataDetails)
                .getJson();

        assertThat(response.getContentAsString()).isEqualTo(dataDetailsConsultation);
    }

}
