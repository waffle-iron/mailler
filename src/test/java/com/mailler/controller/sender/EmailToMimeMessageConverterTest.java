package com.mailler.controller.sender;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.context.Context;

import com.google.common.collect.Maps;
import com.mailler.controller.converter.MapPropertiesToEmailContextConverter;

@RunWith(MockitoJUnitRunner.class)
public class EmailToMimeMessageConverterTest {

	@Mock
	private EmailTemplateEngineProcessor templateEngine;
	
	private JavaMailSender mailSender = new JavaMailSenderImpl();
	
	@Mock
	private MapPropertiesToEmailContextConverter mapToContextConverter;
	
	@Mock
	private Context context;
	
	private EmailToMimeMessageConverter converter;
	
	@Before
	public void setUp() {
		converter = new EmailToMimeMessageConverter(templateEngine, mailSender, mapToContextConverter);
	}
	
	@Test
	public void shouldConvertAnEmailIntoMimeMessage() throws Exception {
		Map<String, String> someContent = Maps.newHashMap();
		someContent.put("name", "Alexandre Gama");
		
		Email email = Email.newEmail()
			.from("alexandre.gama@elo7.com")
			.to("alexandre@gmail.com")
			.withSubject("Welcome")
			.containing(someContent)
			.usingTheTemplate("email.html")
			.build();
		
		String htmlContent = "Some content";

		when(mapToContextConverter.convertFrom(someContent)).thenReturn(context);
		when(templateEngine.process(email.getTemplate(), context)).thenReturn(htmlContent);
		
		MimeMessage mimeMessage = converter.convertFrom(email);
		
		InternetAddress addressFrom = (InternetAddress) mimeMessage.getFrom()[0];
		InternetAddress addressTo = (InternetAddress) mimeMessage.getRecipients(RecipientType.TO)[0];
		
		assertThat(addressFrom.getAddress()).isEqualTo("alexandre.gama@elo7.com");
		assertThat(addressTo.getAddress()).isEqualTo("alexandre@gmail.com");
		assertThat(mimeMessage.getSubject()).isEqualTo("Welcome");
	}
	
}
