package com.devsim.droneapp.services;

import com.devsim.droneapp.configuration.DroneConfiguration;
import com.devsim.droneapp.dtos.CreateDroneDto;
import com.devsim.droneapp.dtos.MedicationDto;
import com.devsim.droneapp.entities.Drone;
import com.devsim.droneapp.entities.Medication;
import com.devsim.droneapp.enums.State;
import com.devsim.droneapp.exceptions.DroneLoadingStatusException;
import com.devsim.droneapp.exceptions.DroneLowBatteryException;
import com.devsim.droneapp.exceptions.DroneMaxWeightExceededException;
import com.devsim.droneapp.exceptions.DroneNotFoundException;
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


    @Override
    public CreateDroneDto registerDrone(CreateDroneDto createDroneDto) {
        Drone drone = new Drone();

        drone.setSerialNumber(createDroneDto.getSerialNumber());
        drone.setModel(createDroneDto.getModel());
        drone.setWeightLimit(createDroneDto.getWeightLimit());
        drone.setBatteryCapacity(createDroneDto.getBatteryCapacity());
        drone.setState(State.IDLE);

        droneRepository.save(drone);
        createDroneDto.setId(drone.getId());
        createDroneDto.setState(State.IDLE);

        return createDroneDto;
    }

    @Override
    @Transactional
    public MedicationDto loadDrone(Long droneId, MedicationDto medicationDto) throws Exception {

        // fetchDrone
        Drone drone = getDrone(droneId);

        //check drone status
        validateDroneState(drone);

        // check drone battery level
        validateDroneBatteryLevel(drone);

        // check drone max limit is not exceeded
        validateDroneWeight(drone, medicationDto);

        if (drone.getState().equals(State.IDLE)) {
            drone.setState(State.LOADING);
            droneRepository.save(drone);
        }

        Medication medication = new Medication();

        medication.setName(medicationDto.getName());
        medication.setCode(medicationDto.getCode());
        medication.setWeight(medicationDto.getWeight());
        medication.setImage(medicationDto.getImage());
        medication.setDrone(drone);

        medicationRepository.save(medication);

        medicationDto.setId(medication.getId());

        return medicationDto;
    }

    private void validateDroneWeight(Drone drone, MedicationDto medicationDto) throws DroneMaxWeightExceededException {
        int currentDroneWeight = Optional.ofNullable(drone.getMedication())
                .orElse(Collections.emptyList())
                .stream()
                .map(Medication::getWeight)
                .reduce(0, Integer::sum);

        if((currentDroneWeight + medicationDto.getWeight()) > drone.getWeightLimit()){
            throw new DroneMaxWeightExceededException("Drone maximum loading weight exceeded");
        }
    }

    private void validateDroneBatteryLevel(Drone drone) throws DroneLowBatteryException {
        DroneConfiguration.Battery battery = droneConfiguration.getBattery();
        if (drone.getBatteryCapacity() < battery.getLevel()) {
            throw new DroneLowBatteryException("Drone battery level is low");
        }
    }

    private void validateDroneState(Drone drone) throws DroneLoadingStatusException {
        if (!drone.getState().equals(State.IDLE) && !drone.getState().equals(State.LOADING)) {
            throw new DroneLoadingStatusException("Can't load drone while not in state IDLE/LOADING");
        }
    }


    @Override
    public List<Drone> getAvailableDrones() {
        return (List<Drone>) droneRepository.findAll();
    }

    @Override
    public Drone getDrone(Long droneId) throws DroneNotFoundException {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(DroneNotFoundException::new);

        return drone;
    }
}
