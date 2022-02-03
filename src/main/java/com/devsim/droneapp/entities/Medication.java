package com.devsim.droneapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "medications", schema = "drone")
public class Medication implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "image_url")
    private String image;

    @ManyToOne
    @JoinColumn(name="drone_id")
    @JsonBackReference
    private Drone drone;

}
