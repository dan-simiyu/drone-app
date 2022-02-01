package com.devsim.droneapp.exceptions;

public class DroneLoadingStatusException extends Exception{
    public DroneLoadingStatusException() {
        super("Can't load drone while not in state IDLE/LOADING");
    }

    public DroneLoadingStatusException(String message) {
        super(message);
    }
}
