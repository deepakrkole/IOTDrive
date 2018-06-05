package com.IOTDrive;

import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IOTDriveApplication {

    public static void main(String[] args) {
        SpringApplication.run(IOTDriveApplication.class, args);
    }
}
