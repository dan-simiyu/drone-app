package com.devsim.droneapp.dtos;

import com.devsim.droneapp.enums.Model;
import com.devsim.droneapp.enums.State;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class CreateDroneDto {

    private long id;

    @Size(min = 5, max = 100, message = "Serial Number must be between 5 and 100 characters")
    @NotBlank(message = "Drone serial number is required")
    @JsonProperty
    private String serialNumber;

    @NotNull(message = "Drone model is required")
    @JsonProperty
    private Model model;


    @JsonProperty
    private int weightLimit;


    @JsonProperty
    private int batteryCapacity;

    @NotNull(message = "Drone state is required")
    private State state;

}
