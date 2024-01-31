package com.TM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableCaching
public class SbtmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbtmApplication.class, args);
	}
	
	 
}
