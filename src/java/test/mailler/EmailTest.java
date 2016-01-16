package mailler;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailler.controller.Email;

public class EmailTest {

	@Test
	public void mapToJson() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, String> infos = new HashMap<>();
		infos.put("firstKey", "First Value");
		infos.put("secondKey", "Second Value");
		
		String json = mapper.writeValueAsString(infos);
		System.out.println(json);
	}

	@Test
	public void emailToJson() throws Exception {
		Email email = new Email();
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(email);
		
		System.out.println(json);
	}
	
	@Test
	public void jsonToEmail() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		String json = "{\"email_to\":\"alexandre@gmail.com\",\"subject\":\"Bem vindo!\",\"content\":\"Bem vindo ao Procurando Ape\"}";
		Email email = mapper.convertValue(json, Email.class);
		
		System.out.println(email);
	}
	
	@Test
	public void canDeserialize() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		boolean canSerialize = mapper.canSerialize(Email.class);
		
		assertTrue(canSerialize);
	}
}
