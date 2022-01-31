package com.devsim.droneapp.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@ConfigurationProperties(prefix = "drone")
public class DroneConfiguration {

    private Battery battery;

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public static class Battery {
        @Max(100)
        @Min(0)
        private Integer level;

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }
    }
}
