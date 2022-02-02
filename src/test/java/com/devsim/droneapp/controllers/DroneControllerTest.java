package com.devsim.droneapp.controllers;

import com.devsim.droneapp.dtos.CreateDroneDto;
import com.devsim.droneapp.dtos.MedicationDto;
import com.devsim.droneapp.enums.Model;
import com.devsim.droneapp.enums.State;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DroneControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("should successfully register drone")
    public void testRegisterDrone() throws Exception {
        CreateDroneDto dto = CreateDroneDto.builder()
                .serialNumber("A-2022")
                .batteryCapacity(100)
                .model(Model.HEAVYWEIGHT)
                .state(State.IDLE)
                .weightLimit(500)
                .build();

        this.mvc.perform(post("/api/drone/save")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.model").value(Model.HEAVYWEIGHT.name()))
                .andExpect(jsonPath("$.batteryCapacity").value(100));
    }

    @ParameterizedTest
    @DisplayName("should create drone with valid serial-number")
    @CsvSource({"A0000,201", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa,400"})
    public void testValidSerialNumber(String serialNumber, int statusCode) throws Exception {
        CreateDroneDto dto = CreateDroneDto.builder()
                .serialNumber(serialNumber)
                .batteryCapacity(100)
                .model(Model.HEAVYWEIGHT)
                .state(State.IDLE)
                .weightLimit(500)
                .build();

        this.mvc.perform(post("/api/drone/save")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().is(statusCode));
    }


}
