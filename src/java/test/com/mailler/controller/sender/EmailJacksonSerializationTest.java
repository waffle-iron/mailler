package com.mailler.controller.sender;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mailler.controller.sender.Content;
import com.mailler.controller.sender.Email;

public class EmailJacksonSerializationTest {

	@Test
	public void shouldCheckIfEmailObjectIsTheSameAsTheEmailJsonObject() throws Exception {
		Map<String, String> properties = new HashMap<>();
		properties.put("name", "Alexandre Gama");
		properties.put("message", "Welcome to Mailler Gama");
		Content bodyContent = new Content();
		bodyContent.setProperties(properties);
		
		Email email = new Email();
		email.setEmailFrom("alexandre.gama@elo7.com");
		email.setEmailTo("alexandre.gama.lima@gmail.com");
		email.setTemplate("http://s3.amazon.com/mailler/template/email.html");
		email.setContent(bodyContent);
		email.setSubject("Welcome");
		
		ObjectWriter prettyWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
		prettyWriter.writeValue(new File("email-pretty.json"), email);
		
		ObjectWriter writer = new ObjectMapper().writer();
		writer.writeValue(new File("email-ugly.json"), email);
		
		ObjectMapper mapper = new ObjectMapper();
		Email emailFromJson = mapper.readValue(new File("email-pretty.json"), Email.class);
		
		assertThat(emailFromJson.getEmailFrom(), is(equalTo("alexandre.gama@elo7.com")));
		assertThat(emailFromJson.getEmailTo(), is(equalTo("alexandre.gama.lima@gmail.com")));
		assertThat(emailFromJson.getSubject(), is(equalTo("Welcome")));
		assertThat(emailFromJson.getTemplate(), is(equalTo("http://s3.amazon.com/mailler/template/email.html")));
		
		assertThat(emailFromJson.getContent().getProperties(), hasValue("Alexandre Gama"));
		assertThat(emailFromJson.getContent().getProperties(), hasValue("Welcome to Mailler Gama"));
	}
	
}
