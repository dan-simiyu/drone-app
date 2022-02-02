package com.devsim.droneapp.configuration;

import com.devsim.droneapp.entities.Drone;
import com.devsim.droneapp.entities.DroneBatteryAuditLog;
import com.devsim.droneapp.repositories.DroneBatteryLevelAuditLogsRepo;
import com.devsim.droneapp.repositories.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DroneBatteryLevelScheduler {
    private static final Logger log = LoggerFactory.getLogger(DroneBatteryLevelScheduler.class);
    private final DroneRepository droneRepository;
    private final DroneBatteryLevelAuditLogsRepo droneBatteryLevelAuditLogsRepo;
    private boolean auditDroneBatteryStatus = false;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    public DroneBatteryLevelScheduler(DroneRepository droneRepository, DroneBatteryLevelAuditLogsRepo droneBatteryAuditRepository) {
        this.droneRepository = droneRepository;
        this.droneBatteryLevelAuditLogsRepo = droneBatteryAuditRepository;
    }

    @Scheduled(fixedRate = 60000, initialDelay = 60000)
    public void auditDroneBattery() {
        log.info("Running Drone Audit Log at {}", dateFormat.format(new Date()));
        if(!auditDroneBatteryStatus){
            auditDroneBatteryStatus = true;
            Iterable<Drone> all = droneRepository.findAll();
            List<DroneBatteryAuditLog> droneBatteryLevelAuditList = new ArrayList<>();
            all.forEach(drone -> droneBatteryLevelAuditList.add(new DroneBatteryAuditLog(drone)));
            droneBatteryLevelAuditLogsRepo.saveAll(droneBatteryLevelAuditList);
            auditDroneBatteryStatus = false;
        }
    }
}
