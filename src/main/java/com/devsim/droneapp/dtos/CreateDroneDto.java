package com.devsim.droneapp.dtos;

import com.devsim.droneapp.enums.Model;
import com.devsim.droneapp.enums.State;
import com.devsim.droneapp.validations.SerialNumber;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class CreateDroneDto {
    @SerialNumber(regex = "[a-z]")
    @NotBlank(message = "Serial number is mandatory")
    private String serialNumber;

    private Model model;

    @Max(500)
    private int weightLimit;

    @Min(0)
    @Max(100)
    private int batteryCapacity;

    private State state;
}
