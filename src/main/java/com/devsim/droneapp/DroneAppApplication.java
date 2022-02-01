package com.devsim.droneapp;

import com.devsim.droneapp.configuration.DroneConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(value = {DroneConfiguration.class})
@EnableScheduling
public class DroneAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneAppApplication.class, args);
	}

}
