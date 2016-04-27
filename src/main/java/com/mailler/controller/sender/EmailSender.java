package com.mailler.controller.sender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailSender.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailToMimeMessageConverter emailConverter;
	
	@Deprecated //Spring eyes only
	EmailSender() {
	}
	
	public EmailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void send(Email email) throws MessagingException {
		LOGGER.info("Email received: " + email);
		
		MimeMessage message = emailConverter.convertFrom(email);
		
		mailSender.send(message);
	}
	
}
