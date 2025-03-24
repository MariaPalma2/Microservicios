package com.maria.microservicio_hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicioHotelApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroservicioHotelApplication.class, args);
	}
}