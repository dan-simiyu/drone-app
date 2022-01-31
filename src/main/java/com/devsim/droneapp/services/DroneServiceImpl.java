package com.devsim.droneapp.services;

import com.devsim.droneapp.entities.Drone;
import com.devsim.droneapp.entities.Medication;
import com.devsim.droneapp.enums.State;
import com.devsim.droneapp.repositories.DroneRepository;
import com.devsim.droneapp.repositories.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneServiceImpl implements DroneService{
    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    private static final int LOW_BATTERY_LEVEL = 25;

    @Override
    public Drone registerDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    @Override
    public Medication loadDrone(Long droneId, Medication medication) {
        Drone drone = validateDroneLoading(droneId, medication);

        if(drone.getState().equals(State.IDLE)){
            drone.setState(State.LOADING);
            droneRepository.save(drone);
        }

        medication.setDrone(drone);
        medicationRepository.save(medication);

        return medication;
    }

    private Drone validateDroneLoading(Long droneId, Medication medication) throws IllegalArgumentException {
        Optional<Drone> optionalDrone = droneRepository.findById(droneId);

        if(!optionalDrone.isPresent()){
            throw new IllegalArgumentException("Drone not found");
        }

        Drone drone = optionalDrone.get();
        if(!drone.getState().equals(State.IDLE) && !drone.getState().equals(State.LOADING)){
            throw new IllegalArgumentException("Can't load drone while not in state IDLE/LOADING");
        }

        if(drone.getBatteryCapacity() < LOW_BATTERY_LEVEL){
            throw new IllegalArgumentException("Drone battery is low");
        }

        return optionalDrone.get();
    }



    @Override
    public List<Drone> getAvailableDrones() {
        return(List<Drone>)droneRepository.findAll();
    }

    @Override
    public Drone getDrone(Long droneId) throws IllegalArgumentException{
        Optional<Drone> optionalDrone = droneRepository.findById(droneId);

        if (optionalDrone.isPresent()) {
            return optionalDrone.get();
        } else {
            throw new IllegalArgumentException("Drone not found");
        }
    }
}
