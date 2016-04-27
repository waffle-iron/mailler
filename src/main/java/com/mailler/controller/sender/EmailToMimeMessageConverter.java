package com.mailler.controller.sender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import com.mailler.controller.converter.MapPropertiesToEmailContextConverter;

@Component
public class EmailToMimeMessageConverter {
	
	@Autowired
	private EmailTemplateEngineProcessor templateEngine;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MapPropertiesToEmailContextConverter mapToContextConverter;
	
	public EmailToMimeMessageConverter(EmailTemplateEngineProcessor templateEngine, JavaMailSender mailSender,
			MapPropertiesToEmailContextConverter mapToContextConverter) {
		this.templateEngine = templateEngine;
		this.mailSender = mailSender;
		this.mapToContextConverter = mapToContextConverter;
	}

	/* Spring eyes only */
	EmailToMimeMessageConverter() {
	}

	public MimeMessage convertFrom(Email email) throws MessagingException {
		Context bodyContext = mapToContextConverter.convertFrom(email.getContent().getProperties());
		
		final String htmlContent = templateEngine.process(email.getTemplate(), bodyContext);
		
		MimeMessage message = newMessage()
			.withSubject(email.getSubject())
			.fromUser(email.getEmailFrom())
			.sendingTo(email.getEmailTo())
			.withContentUsingHtml(htmlContent)
			.build();
		
		return message;
	}
	
	private MimeMessageBuilder newMessage() throws MessagingException {
		return new MimeMessageBuilder();
	}
	
	private class MimeMessageBuilder {

		private MimeMessage message;
		
		private MimeMessageHelper messageHelper;
		
		public MimeMessageBuilder() throws MessagingException {
			boolean allowingMultipart = true;
			message = mailSender.createMimeMessage();
			messageHelper = new MimeMessageHelper(message, allowingMultipart);
		}
		
		public MimeMessageFromBuilder withSubject(String subject) throws MessagingException {
			message.setSubject(subject);
			return new MimeMessageFromBuilder();
		}
		
		private class MimeMessageFromBuilder {
			
			public MimeMessageToBuilder fromUser(String emailFrom) throws MessagingException {
				messageHelper.setFrom(emailFrom);
				return new MimeMessageToBuilder();
			}
			
			private class MimeMessageToBuilder {
				
				public MimeMessageContentBuilder sendingTo(String emailTo) throws MessagingException {
					messageHelper.setTo(emailTo);
					return new MimeMessageContentBuilder();
				}
			}
			
			private class MimeMessageContentBuilder {
				
				public MimeMessageBuilt withContentUsingHtml(String content) throws MessagingException {
					boolean usingHtml = true;
					messageHelper.setText(content, usingHtml);
					return new MimeMessageBuilt();
				}
			}
			
			private class MimeMessageBuilt {
				
				public MimeMessage build() {
					return message;
				}
			}
		}
	}
	
}
