#!/bin/bash

GREE_COLOR='\033[0;32m'

COLOR_OFF='\033[0m'

QUEUE_URL=https://sqs.us-east-1.amazonaws.com/669856304367/dev-email
printf "$GREE_COLOR Sending message for the queue $COLOR_OFF:\n $QUEUE_URL\n"

MESSAGE_BODY='{"email_from":"alexandre.gama@elo7.com","email_to":"alexandre.gama.lima@gmail.com","subject":"Welcome","content":{"properties":{"name":"AlexandreGama","message":"Welcome"}},"template":"https://s3.amazonaws.com/mailler-email-template/email-template-welcome.html"}'
printf "$GREE_COLOR Sending the message $COLOR_OFF:\n $MESSAGE_BODY\n"

MESSAGE_ATTRIBUTES='{"className":{"StringValue":"com.mailler.Email","DataType":"String"},"contentType":{"StringValue":"application/json","DataType":"String"}}'
echo "$GREE_COLOR Sending message with attributes $COLOR_OFF:\n $MESSAGE_ATTRIBUTES\n"

aws sqs send-message --queue-url $QUEUE_URL --message-body $MESSAGE_BODY --message-attributes $MESSAGE_ATTRIBUTES
