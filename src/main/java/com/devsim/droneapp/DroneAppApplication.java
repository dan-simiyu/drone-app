package com.devsim.droneapp;

import com.devsim.droneapp.configuration.DroneConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {DroneConfiguration.class})
public class DroneAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneAppApplication.class, args);
	}

}
