package com.clone.ecommerece;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ECommereceCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommereceCloneApplication.class, args);
		System.out.println("Ran Successfully");
	}
}
