package com.mailler.controller.sender;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;

import org.junit.Test;

import com.google.common.collect.Maps;

public class EmailBuilderTest {

	@Test
	public void shouldCreateANewEmailUsingEmailBuilder() throws Exception {
		HashMap<String, String> propertiesToContent = Maps.newHashMap();
		propertiesToContent.put("name", "Alexandre Gama");
		
		Email email = Email.newEmail()
			.from("alexandre.gama@elo7.com")
			.to("alexandre.gama.lima@gmail.com")
			.withSubject("Welcome")
			.containing(propertiesToContent)
			.usingTheTemplate("email.html")
			.build();
		
		assertThat(email.getEmailFrom()).isEqualTo("alexandre.gama@elo7.com");
		assertThat(email.getEmailTo()).isEqualTo("alexandre.gama.lima@gmail.com");
		assertThat(email.getSubject()).isEqualTo("Welcome");
		assertThat(email.getTemplate()).isEqualTo("email.html");
		assertThat(email.getContent().getProperties().get("name")).isEqualTo("Alexandre Gama");
	}
	
}
