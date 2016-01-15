package com.mailler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.AmazonSQS;

@Component
public class SqsQueueSender {

	private final QueueMessagingTemplate queueMessagingTemplate;

	@Autowired
	public SqsQueueSender(AmazonSQS amazonSqs) {
		this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSqs);
	}
	
	public void send(String message) {
		this.queueMessagingTemplate.send("https://sqs.us-east-1.amazonaws.com/669856304367/dev-image-crop"	, MessageBuilder.withPayload(message).build());
	}
}
