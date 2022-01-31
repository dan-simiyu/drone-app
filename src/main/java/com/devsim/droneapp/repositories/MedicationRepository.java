package com.devsim.droneapp.repositories;

import com.devsim.droneapp.entities.Medication;
import org.springframework.data.repository.CrudRepository;

public interface MedicationRepository extends CrudRepository<Medication, Long> {
}
