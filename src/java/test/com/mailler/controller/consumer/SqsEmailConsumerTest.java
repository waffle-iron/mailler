package com.mailler.controller.consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.mailler.controller.sender.Email;
import com.mailler.controller.sender.EmailSender;

@RunWith(MockitoJUnitRunner.class)
public class SqsEmailConsumerTest {

	@Mock
	private EmailSender sender;
	
	@Mock
	private Email email;
	
	@InjectMocks
	private SqsEmailConsumer consumer;
	
	@Test
	public void shouldSendANewEmailWhenEmailConsumerReceiveANewMessage() throws Exception {
		consumer.receive(email);
		
		Mockito.verify(sender).send(email);
	}
	
}
