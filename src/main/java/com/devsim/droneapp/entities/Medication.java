package com.devsim.droneapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medications")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
