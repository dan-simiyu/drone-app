package com.devsim.droneapp.entities;

import com.devsim.droneapp.enums.Model;
import com.devsim.droneapp.enums.State;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "drones")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    @NotBlank(message = "Serial number is mandatory")
    private String serialNumber;

    @Column()
    @Enumerated(EnumType.STRING)
    private Model model;

    @Column()
    private int weightLimit;

    @Column()
    @Range(min = 0, max = 100)
    private int batteryCapacity;

    @Column()
    @Enumerated(EnumType.STRING)
    private State state;

    @JsonManagedReference
    @OneToMany(mappedBy = "drone", fetch = FetchType.EAGER)
    private List<Medication> medication;


}
