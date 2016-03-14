package com.mailler.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;

import com.mailler.controller.sender.EmailSender;

@Component
public class SqsEmailConsumer {

	@Autowired
	private EmailSender emailSender;
	
	public SqsEmailConsumer() {
	}

	@MessageMapping("https://sqs.us-east-1.amazonaws.com/669856304367/dev-email")
	public void receive(Email email) throws MessagingException {
		emailSender.send(email);
	}
	
}
