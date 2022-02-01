package com.devsim.droneapp.enums;

public enum Model {
    LIGHTWEIGHT("LIGHTWEIGHT"),
    MIDDLEWEIGHT("MIDDLEWEIGHT"),
    CRUISERWEIGHT("CRUISERWEIGHT"),
    HEAVYWEIGHT("HEAVYWEIGHT");

    public String value;

    private Model(String model){
        value = model;
    }
}
