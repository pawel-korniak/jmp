package com.gitlab.pawelkorniak.courierapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourierAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CourierAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		for (;;) {
//			Thread.sleep(10000);
//
//			System.out.println("sleep");
//		}
	}
}
