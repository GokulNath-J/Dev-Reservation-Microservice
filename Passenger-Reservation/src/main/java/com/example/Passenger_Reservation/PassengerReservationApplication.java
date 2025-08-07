package com.example.Passenger_Reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PassengerReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassengerReservationApplication.class, args);
	}

}
