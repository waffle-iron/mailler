package com.mailler.controller.sender;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
		 
		final String htmlContent = this.templateEngine.process("email-template.html", template);
		
		MimeMessage message = mailSender.createMimeMessage();
		message.setSubject(email.getSubject());
		
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		messageHelper.setTo(email.getEmailTo());
		messageHelper.setFrom(email.getEmailFrom());
		messageHelper.setText(htmlContent, true);
		
		System.out.println("Sending message: " + message);
		this.mailSender.send(message);
	}
	
}
