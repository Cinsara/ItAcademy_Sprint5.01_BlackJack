package com.example.demo;

import org.springframework.boot.SpringApplication;

public class TestBlackjackApplication {

	public static void main(String[] args) {
		SpringApplication.from(BlackjackApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
