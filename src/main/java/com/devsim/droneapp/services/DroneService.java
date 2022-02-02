package com.devsim.droneapp.services;

import com.devsim.droneapp.dtos.CreateDroneDto;
import com.devsim.droneapp.dtos.MedicationDto;
import com.devsim.droneapp.entities.Drone;

import java.util.List;

public interface DroneService {
    CreateDroneDto registerDrone(CreateDroneDto createDroneDto) throws Exception;
    MedicationDto loadDrone(Long droneId, MedicationDto medicationDto) throws Exception;
    List<Drone> getAvailableDrones();
    Drone getDrone(Long droneId) throws Exception;
}
