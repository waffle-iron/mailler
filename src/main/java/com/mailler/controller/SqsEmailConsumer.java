package com.mailler.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;

@Component
public class SqsEmailConsumer {

	@MessageMapping("https://sqs.us-east-1.amazonaws.com/669856304367/dev-email")
	public void receive(Email email) {
		System.out.println("Email received: " + email);
	}
	
}
