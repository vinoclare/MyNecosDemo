package com.example.demo;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCasClient
public class CasSsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasSsoApplication.class, args);
	}

}
