package com.devsim.droneapp.services;


import com.devsim.droneapp.configuration.DroneConfiguration;
import com.devsim.droneapp.dtos.MedicationDto;
import com.devsim.droneapp.entities.Drone;
import com.devsim.droneapp.enums.State;
import com.devsim.droneapp.exceptions.DroneLoadingStatusException;
import com.devsim.droneapp.exceptions.DroneLowBatteryException;
import com.devsim.droneapp.repositories.DroneRepository;
import com.devsim.droneapp.repositories.MedicationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DroneServiceImplTest {

    private DroneServiceImpl droneService;

    @Mock
    private DroneRepository droneRepository;
    @Mock
    private MedicationRepository medicationRepository;
    @Mock
    private DroneConfiguration droneConfiguration;

    @BeforeEach
    void beforeEach() {


        when(droneConfiguration.getBattery()).then(invocationOnMock -> {
            DroneConfiguration.Battery battery = mock(DroneConfiguration.Battery.class);
            when(battery.getLevel()).thenReturn(25);

            return battery;
        });

        droneService = new DroneServiceImpl(droneRepository, medicationRepository, droneConfiguration);
    }

    @ParameterizedTest
    @DisplayName("should validate loading drone in the correct state")
    @EnumSource(value = State.class, names = {"LOADED", "DELIVERING", "DELIVERED", "RETURNING"})
    void validateValidLoadingDroneState(State state) {
        Drone drone = new Drone();
        drone.setId(1L);
        drone.setState(state);

        when(droneRepository.findById(1L))
                .thenReturn(Optional.of(drone));

        assertThrows(DroneLoadingStatusException.class,
                () -> droneService.loadDrone(drone.getId(), new MedicationDto()));

        // verify(droneRepository.findById(1L), atMostOnce());
    }

    @Test
    @DisplayName("should validate drone battery level not less than threshold configured")
    void validateValidDroneBatteryLevel(){
        Drone drone = new Drone();
        drone.setId(1L);
        drone.setState(State.IDLE);
        drone.setBatteryCapacity(24);

        when(droneRepository.findById(1L))
                .thenReturn(Optional.of(drone));

        assertThrows(DroneLowBatteryException.class,
                () -> droneService.loadDrone(1L, new MedicationDto()));
    }




}
