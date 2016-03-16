package com.mailler.controller.sender;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.mailler.controller.Email;

@Component
public class EmailSender {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	@Autowired
	private EmailToMessageConverter emailConverter;
	
	@Deprecated //Spring eyes only
	EmailSender() {
	}
	
	public EmailSender(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
	}
	
	public void send(Email email) throws MessagingException {
		System.out.println("Email received: " + email);
		
		final Context template = new Context(Locale.getDefault());
		template.setVariable("name", "RecipientName");
		template.setVariable("subscriptionDate", new Date());
		template.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
		template.setVariable("imageResourceName", ""); // so that we can reference it from HTML
		
		final String htmlContent = this.templateEngine.process("https://s3.amazonaws.com/mailler-email-template/email-template.html", template);
		
		MimeMessage message = emailConverter.convertFrom(email, htmlContent, mailSender);
		
		System.out.println("Sending message: " + message);
		this.mailSender.send(message);
	}
	
}
