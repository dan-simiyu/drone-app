package com.devsim.droneapp.exceptions;

public class DroneLowBatteryException extends Exception {
    public DroneLowBatteryException() {
        super("Drone battery level is low");
    }

    public DroneLowBatteryException(String message) {
        super(message);
    }
}
