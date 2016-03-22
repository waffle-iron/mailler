package com.mailler.controller.sender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.spring4.SpringTemplateEngine;

@RunWith(MockitoJUnitRunner.class)
public class EmailToMimeMessageConverterTest {

	@Mock
	private SpringTemplateEngine templateEngine;
	
	@Mock
	private JavaMailSender mailSender;
	
	@Test
	public void shouldConvertAnEmailIntoMimeMessage() throws Exception {
		
	}
	
}
