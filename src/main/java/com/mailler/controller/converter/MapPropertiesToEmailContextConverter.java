package com.mailler.controller.converter;

import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

@Component
public class MapPropertiesToEmailContextConverter {

	public Context convertFrom(Map<String, String> properties) {
		final Context bodyContent = new Context(Locale.getDefault());
		
		properties.keySet().stream().forEach(key -> bodyContent.setVariable(key, properties.get(key)));
		
		return bodyContent;
	}

}
