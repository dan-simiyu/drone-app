package com.devsim.droneapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "medications")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private int weight;

    @Column
    private String code;

    @Column
    private String image;

    @ManyToOne
    @JoinColumn(name="drone_id")
    @JsonBackReference
    private Drone drone;

}
