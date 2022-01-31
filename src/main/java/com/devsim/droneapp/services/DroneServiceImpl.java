package com.devsim.droneapp.services;

import com.devsim.droneapp.configuration.DroneConfiguration;
import com.devsim.droneapp.dtos.CreateDroneDto;
import com.devsim.droneapp.entities.Drone;
import com.devsim.droneapp.entities.Medication;
import com.devsim.droneapp.enums.State;
import com.devsim.droneapp.repositories.DroneRepository;
import com.devsim.droneapp.repositories.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final DroneConfiguration droneConfiguration;


    private static final int LOW_BATTERY_LEVEL = 25;

    @Override
    public Drone registerDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    @Override
    public Drone registerDrone(CreateDroneDto createDroneDto) {
        Drone drone = Drone.builder()
                .serialNumber(createDroneDto.getSerialNumber())
                .batteryCapacity(createDroneDto.getBatteryCapacity())
                .model(createDroneDto.getModel())
                .state(createDroneDto.getState())
                .weightLimit(createDroneDto.getWeightLimit())
                .build();
        return registerDrone(drone);
    }

    @Override
    @Transactional
    public Medication loadDrone(Long droneId, Medication medication) {
        // fetchDrone(droneId)
        // validateDroneState(drone)
        // validateDroneBatteryLevel(drone)
        // validateDroneWeight(drone, medication)
        Drone drone = validateDroneLoading(droneId, medication);

        if (drone.getState().equals(State.IDLE)) {
            drone.setState(State.LOADING);
            droneRepository.save(drone);
        }

        medication.setDrone(drone);
        medicationRepository.save(medication);

        return medication;
    }

    private Drone validateDroneLoading(Long droneId, Medication medication) throws IllegalArgumentException {
        /*Optional<Drone> optionalDrone = droneRepository.findById(droneId);

        if(!optionalDrone.isPresent()){
            throw new IllegalArgumentException("Drone not found");
        }

        Drone drone = optionalDrone.get();*/
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new IllegalArgumentException("Drone with id " + droneId + " not found"));

        // immutable
        int weightAllocatedThusFar = Optional.ofNullable(drone.getMedication())
                .orElse(Collections.emptyList())
                .stream()
                .map(Medication::getWeight)
                .reduce(0, Integer::sum);

        //(medication.getWeight() + weightAllocatedThusFar) <= drone.getWeightLimit()

        if (!drone.getState().equals(State.IDLE) && !drone.getState().equals(State.LOADING)) {
            throw new IllegalArgumentException("Can't load drone while not in state IDLE/LOADING");
        }

        DroneConfiguration.Battery battery = droneConfiguration.getBattery();
        if (drone.getBatteryCapacity() < battery.getLevel()) {
            throw new IllegalArgumentException("Drone battery is low");
        }

        return drone;
    }


    @Override
    public List<Drone> getAvailableDrones() {
        return (List<Drone>) droneRepository.findAll();
    }

    @Override
    public Drone getDrone(Long droneId) throws IllegalArgumentException {
        Optional<Drone> optionalDrone = droneRepository.findById(droneId);

        if (optionalDrone.isPresent()) {
            return optionalDrone.get();
        } else {
            throw new IllegalArgumentException("Drone not found");
        }
    }
}
