package com.devsim.droneapp.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class MedicationDto {

    private long id;

    @NotBlank(message = "Medication name is required")
    @Pattern( regexp = "^[a-zA-Z0-9_\\-]+$", message="Name can only contain letters, numbers, '-', '_'")
    private String name;

    private int weight;

    @NotBlank(message = "Medication code is required")
    @Pattern( regexp = "^[A-Z0-9_]+$", message="Name can only contain letters, numbers, '-', '_'")
    private String code;

    private String image;

}
