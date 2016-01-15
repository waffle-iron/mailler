package com.mailler.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;

@Component
public class SqsQueueConsumer {

	@MessageMapping("https://sqs.us-east-1.amazonaws.com/669856304367/dev-image-crop")
	public void consumes(String name) {
		System.out.println("Consumed Message: " + name);
	}
}
