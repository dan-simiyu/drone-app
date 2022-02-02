package com.devsim.droneapp.entities;

import com.devsim.droneapp.enums.Model;
import com.devsim.droneapp.enums.State;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "drones")
public class Drone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "serial_number", unique = true, length = 100, nullable = false)
    @NotBlank(message = "Serial number is mandatory")
    private String serialNumber;

    @Column(name = "model", nullable = false)
    @Enumerated(EnumType.STRING)
    private Model model;

    @Column(name = "weight_limit", nullable = false)
    private int weightLimit;

    @Column(name = "battery_capacity", nullable = false)
    @Min(0)
    @Max(100)
    private int batteryCapacity;

    @Column(name = "state", nullable = false )
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "medications")
    @JsonManagedReference
    @OneToMany(mappedBy = "drone", fetch = FetchType.EAGER)
    private List<Medication> medication;

}
