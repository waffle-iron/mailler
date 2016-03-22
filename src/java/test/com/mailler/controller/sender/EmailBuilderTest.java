package com.mailler.controller.sender;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.google.common.collect.Maps;

public class EmailBuilderTest {

	@Test
	public void shouldCreateANewEmailUsingEmailBuilder() throws Exception {
		HashMap<String, String> propertiesToContent = Maps.newHashMap();
		propertiesToContent.put("name", "Alexandre Gama");
		
		Email email = Email.newEmail()
			.withEmailFrom("alexandre.gama@elo7.com")
			.withEmailTo("alexandre.gama.lima@gmail.com")
			.withSubject("Welcome")
			.withContent(propertiesToContent)
			.build();
		
		Assertions.assertThat(email.getEmailFrom()).isEqualTo("alexandre.gama@elo7.com");
	}
	
}
