package com.devsim.droneapp.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "drone_battery_audit_logs")
public class DroneBatteryAuditLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="drone_id")
    private Drone drone;

    @Column
    private int droneBatteryLevel;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    public DroneBatteryAuditLog(){}

    public DroneBatteryAuditLog(Drone drone) {
        this.drone = drone;
        this.droneBatteryLevel = drone.getBatteryCapacity();
    }
}
