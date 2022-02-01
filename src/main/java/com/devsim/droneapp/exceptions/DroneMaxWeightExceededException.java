package com.devsim.droneapp.exceptions;

public class DroneMaxWeightExceededException extends Exception{
    public DroneMaxWeightExceededException() {
        super("Drone loading capacity exceeded");
    }

    public DroneMaxWeightExceededException(String message) {
        super(message);
    }
}
