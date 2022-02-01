package com.devsim.droneapp.exceptions;

public class DroneNotFoundException extends Exception{
    public DroneNotFoundException() {
        super("Drone not Found");
    }

    public DroneNotFoundException(String message) {
        super(message);
    }
}
