package com.mailler.controller.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Component
public class EmailTemplateEngineProcessor {

	@Autowired
	private SpringTemplateEngine templateEngine;
	
	public String process(String template, Context context) {
		String templateProcessed = templateEngine.process(template, context);
		
		return templateProcessed;
	}
}
