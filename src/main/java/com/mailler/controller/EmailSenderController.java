package com.mailler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "/email-sender")
@Controller
public class EmailSenderController {

	private MailSender sender;

	@Autowired
	public EmailSenderController(MailSender sender) {
		this.sender = sender;
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String greeting(@RequestParam(value = "emailFrom") String emailFrom, 
			@RequestParam(value = "emailTo") String emailTo) {
		
		System.out.println("Sending email from: " + emailFrom);
		System.out.println("Sending email to: " + emailTo);
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(emailTo);
		message.setFrom(emailFrom);
		message.setSubject("Bem vindo");
		message.setText("Bem vindo ao Procurando Ape");
		this.sender.send(message);		
		
		return "greeting";
	}
	
}
