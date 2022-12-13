package com.intiFormation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FormationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormationApiApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncode() {
		
		return new BCryptPasswordEncoder();
	}
}
