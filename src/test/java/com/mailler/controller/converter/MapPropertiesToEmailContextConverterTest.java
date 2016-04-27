package com.mailler.controller.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.thymeleaf.context.Context;

import com.google.common.collect.Maps;

@RunWith(MockitoJUnitRunner.class)
public class MapPropertiesToEmailContextConverterTest {

	private MapPropertiesToEmailContextConverter converter;
	
	public MapPropertiesToEmailContextConverterTest() {
		converter = new MapPropertiesToEmailContextConverter();
	}
	
	@Test
	public void shouldConvertAMapContentIntoSpringEmailContext() throws Exception {
		Map<String, String> properties = Maps.newHashMap();
		properties.put("name", "Alexandre");
		properties.put("body", "Welcome");
		
		Context context = converter.convertFrom(properties);
		
		assertThat(context.getVariables().get("name")).isEqualTo("Alexandre");
		assertThat(context.getVariables().get("body")).isEqualTo("Welcome");
	}
	
}
