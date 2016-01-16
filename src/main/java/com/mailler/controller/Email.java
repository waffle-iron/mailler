package com.mailler.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

public class Email {

	@JsonProperty("email_to")
	private String emailTo;
	
	@JsonProperty("subject")
	private String subject;
	
	@JsonProperty("content")
	private String content;

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("emailTo", emailTo)
				.add("subject", subject)
				.add("content", content)
				.toString();
	}
}
