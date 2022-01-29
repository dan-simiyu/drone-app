package com.devsim.droneapp.entities;

import com.devsim.droneapp.enums.Model;
import com.devsim.droneapp.enums.State;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Drone {

    private long id;
    private String serialNumber;
    private Model model;
    private int weightLimit;
    private int batteryCapacity;
    private State state;
    private List<Medication> medication;
}
