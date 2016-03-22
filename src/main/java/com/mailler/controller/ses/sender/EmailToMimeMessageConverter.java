package com.mailler.controller.ses.sender;

import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Component
public class EmailToMimeMessageConverter {
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Deprecated // Spring eyes only
	EmailToMimeMessageConverter() {
	}

	public MimeMessage convertFrom(Email email) throws MessagingException {
		final Context bodyContent = new Context(Locale.getDefault());
		Map<String, String> properties = email.getContent().getProperties();
		properties.keySet().stream().forEach(key -> bodyContent.setVariable(key, properties.get(key)));
		
		final String htmlContent = templateEngine.process(email.getTemplate(), bodyContent);
		
		MimeMessage message = mailSender.createMimeMessage();
		boolean allowingMultpart = true;
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, allowingMultpart);
		
		message.setSubject(email.getSubject());
		messageHelper.setTo(email.getEmailTo());
		messageHelper.setFrom(email.getEmailFrom());
		boolean usingHtml = true;
		messageHelper.setText(htmlContent, usingHtml);
		
		return message;
	}
	
	
}
