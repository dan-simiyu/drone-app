package com.devsim.droneapp.controllers;

import com.devsim.droneapp.entities.Drone;
import com.devsim.droneapp.entities.Medication;
import com.devsim.droneapp.services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/drone")
public class DroneController {
    @Autowired
    private  DroneService droneService;

    @PostMapping(value={"", "/"}, consumes = "application/json")
    public ResponseEntity<Drone> register(@Valid @RequestBody Drone drone){
        return ResponseEntity.status(HttpStatus.CREATED).body(droneService.registerDrone(drone));
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Medication> loadDrone(@PathVariable long id, @Valid @RequestBody Medication medication) throws Exception {
        return ResponseEntity.ok(droneService.loadDrone(id, medication));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Drone>> dronesAvailable() {
        return ResponseEntity.ok(droneService.getAvailableDrones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drone> getDrone(@PathVariable long id) throws Exception {
        return ResponseEntity.ok(droneService.getDrone(id));
    }
}
