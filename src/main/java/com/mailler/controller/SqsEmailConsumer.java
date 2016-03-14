package com.mailler.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Component
public class SqsEmailConsumer {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	public SqsEmailConsumer() {
	}

	public SqsEmailConsumer(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
	}
	
	@MessageMapping("https://sqs.us-east-1.amazonaws.com/669856304367/dev-email")
	public void receive(Email email) throws MessagingException {
		System.out.println("Email received: " + email);

		
		final Context ctx = new Context(Locale.getDefault());
		ctx.setVariable("name", "RecipientName");
		ctx.setVariable("subscriptionDate", new Date());
		ctx.setVariable("hobbies", Arrays.asList("Cinema", "Sports", "Music"));
		ctx.setVariable("imageResourceName", ""); // so that we can reference it from HTML
		 
		final String htmlContent = this.templateEngine.process("email-inlineimage.html", ctx);
		
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
