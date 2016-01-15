package com.mailler.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.context.config.annotation.EnableContextCredentials;

@SpringBootApplication
@EnableContextCredentials
public class MaillerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaillerApplication.class, args);
	}
	
}
