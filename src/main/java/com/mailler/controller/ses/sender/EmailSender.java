package com.mailler.controller.ses.sender;

import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Component
public class EmailSender {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	@Autowired
	private EmailToMimeMessageConverter emailConverter;
	
	@Deprecated //Spring eyes only
	EmailSender() {
	}
	
	public EmailSender(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
	}
	
	public void send(Email email) throws MessagingException {
		System.out.println("Email received: " + email);
		
		final Context bodyContent = new Context(Locale.getDefault());
		Map<String, String> properties = email.getContent().getProperties();
		properties.keySet().stream().forEach(key -> bodyContent.setVariable(key, properties.get(key)));
		
		final String htmlContent = this.templateEngine.process(email.getTemplate(), bodyContent);
		
		MimeMessage message = emailConverter.convertFrom(email, htmlContent, mailSender);
		
		System.out.println("Message sent!");
		this.mailSender.send(message);
	}
	
}
