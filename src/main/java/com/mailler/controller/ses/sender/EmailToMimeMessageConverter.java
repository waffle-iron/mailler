package com.mailler.controller.ses.sender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailToMimeMessageConverter {

	public MimeMessage convertFrom(Email email, String htmlContent, JavaMailSender mailSender) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		message.setSubject(email.getSubject());
		
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		messageHelper.setTo(email.getEmailTo());
		messageHelper.setFrom(email.getEmailFrom());
		messageHelper.setText(htmlContent, true);
		return message;
	}

}
