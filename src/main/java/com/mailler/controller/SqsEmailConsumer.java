package com.mailler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;

@Component
public class SqsEmailConsumer {

	private MailSender mailSender;

	@Autowired
	public SqsEmailConsumer(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@MessageMapping("https://sqs.us-east-1.amazonaws.com/669856304367/dev-email")
	public void receive(Email email) {
		System.out.println("Email received: " + email);
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email.getEmailTo());
		message.setFrom(email.getEmailFrom());
		message.setSubject(email.getSubject());
		message.setText(email.getContent());
		
		System.out.println("Sending message: " + message);
		this.mailSender.send(message);
	}
	
}
