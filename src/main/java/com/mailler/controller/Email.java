package com.mailler.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

@JsonInclude(value = Include.NON_NULL)
public class Email {

	@JsonProperty("email_from")
	private String emailFrom;
	
	@JsonProperty("email_to")
	private String emailTo;
	
	@JsonProperty("subject")
	private String subject;
	
	@JsonProperty("content")
	private Content content;
	
	@JsonProperty("template")
	private String template;
	
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
	
	public String getEmailFrom() {
		return emailFrom;
	}
	
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("emailFrom", emailFrom)
				.add("emailTo", emailTo)
				.add("subject", subject)
				.add("content", getContent())
				.toString();
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
}
