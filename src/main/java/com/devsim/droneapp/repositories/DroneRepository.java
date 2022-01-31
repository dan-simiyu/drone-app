package com.devsim.droneapp.repositories;

import com.devsim.droneapp.entities.Drone;
import org.springframework.data.repository.CrudRepository;

public interface DroneRepository extends CrudRepository<Drone, Long> {
}
