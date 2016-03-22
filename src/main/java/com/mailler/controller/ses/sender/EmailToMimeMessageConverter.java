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
		
		MimeMessage message = newMessage()
			.withSubject(email.getSubject())
			.withFromUser(email.getEmailFrom())
			.withTo(email.getEmailTo())
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
			
			public MimeMessageToBuilder withFromUser(String emailFrom) throws MessagingException {
				messageHelper.setFrom(emailFrom);
				return new MimeMessageToBuilder();
			}
			
			private class MimeMessageToBuilder {
				
				public MimeMessageContentBuilder withTo(String emailTo) throws MessagingException {
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
