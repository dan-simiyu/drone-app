package com.devsim.droneapp.services;

import com.devsim.droneapp.entities.Drone;
import com.devsim.droneapp.entities.Medication;

import java.util.List;

public interface DroneService {
    Drone registerDrone(Drone drone);
    Medication loadDrone(Long droneId, Medication medication);
    List<Drone> getAvailableDrones();
    Drone getDrone(Long droneId);
}
