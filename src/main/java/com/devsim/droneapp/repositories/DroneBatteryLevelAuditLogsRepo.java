package com.devsim.droneapp.repositories;

import com.devsim.droneapp.entities.DroneBatteryAuditLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneBatteryLevelAuditLogsRepo extends CrudRepository<DroneBatteryAuditLog,Long> {
}
