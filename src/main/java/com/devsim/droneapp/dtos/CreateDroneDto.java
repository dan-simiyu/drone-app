package com.devsim.droneapp.dtos;

import com.devsim.droneapp.enums.Model;
import com.devsim.droneapp.enums.State;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateDroneDto {

    private long id;

    @NotBlank(message = "Serial number is mandatory")
    @JsonProperty
    private String serialNumber;

    @JsonProperty
    private Model model;


    @JsonProperty
    private int weightLimit;

    @JsonProperty
    private int batteryCapacity;

    private State state;

}
