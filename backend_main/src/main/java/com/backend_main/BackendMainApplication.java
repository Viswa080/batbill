package com.backend_main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BackendMainApplication {

	public static void main(String[] args) {
		Logger log = LogManager.getLogger(BackendMainApplication.class);
		SpringApplication.run(BackendMainApplication.class, args);
		System.out.println(" Application Started ");
		log.info("Started Main Backend Application");
	}

}
