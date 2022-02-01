package com.devsim.droneapp.controllers;

import com.devsim.droneapp.dtos.CreateDroneDto;
import com.devsim.droneapp.dtos.MedicationDto;
import com.devsim.droneapp.entities.Drone;
import com.devsim.droneapp.services.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/drone")
public class DroneController {

    private  DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    public static final Logger logger = LoggerFactory.getLogger(DroneService.class);
    @PostMapping(value= "/save", consumes = "application/json")
    public ResponseEntity<CreateDroneDto> registerDrone(@Valid @RequestBody CreateDroneDto createDroneDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(droneService.registerDrone(createDroneDto));
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<MedicationDto> loadDrone(@PathVariable long id, @Valid @RequestBody MedicationDto medicationDto) throws Exception {
        return ResponseEntity.ok(droneService.loadDrone(id, medicationDto));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Drone>> availableDrones() {
        return ResponseEntity.ok(droneService.getAvailableDrones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drone> getDrone(@PathVariable long id) throws Exception {
        return ResponseEntity.ok(droneService.getDrone(id));
    }
}
