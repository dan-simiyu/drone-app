package com.devsim.droneapp.dtos;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class MedicationDto {

    private long id;

    @Pattern( regexp = "^[a-zA-Z0-9_\\-]+$", message="Name can only contain letters, numbers, '-', '_'")
    private String name;

    private int weight;

    @Pattern( regexp = "^[A-Z0-9_]+$", message="Name can only contain letters, numbers, '-', '_'")
    private String code;

    private String image;

}
