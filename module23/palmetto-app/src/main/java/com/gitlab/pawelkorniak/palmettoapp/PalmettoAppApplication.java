package com.gitlab.pawelkorniak.palmettoapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PalmettoAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PalmettoAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		for (;;) {
//			Thread.sleep(10000);
//			System.out.println("sleep");
//		}
	}
}
