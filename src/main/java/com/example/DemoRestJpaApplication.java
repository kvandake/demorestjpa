package com.example;

import com.example.config.JpaConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Входная точка приложения
 */
@SpringBootApplication
public class DemoRestJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRestJpaApplication.class, args);
	}
}