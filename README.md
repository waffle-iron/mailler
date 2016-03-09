# Mailler
Email sender from Amazon SQS

### Sending a json content to SQS file

We must send the following attributes:

**className**: com.mailler.Email

**contentType**: application/json

### Sending email by EmailSenderController

```bash
http://localhost:8080/email-sender/send?emailFrom=alexandre.gama@elo7.com&emailTo=alexandre.gama@elo7.com
```

### JSON format for Email

The JSON format for the email that will be sent is the following:

```json
{"email_to": "alexandre.gama@elo7.com", "subject": "Welcome!", "content": "Welcome to Elo7!"}
```

### To install the AWS CLI using the bundled installer

```bash
$ curl "https://s3.amazonaws.com/aws-cli/awscli-bundle.zip" -o "awscli-bundle.zip"
$ unzip awscli-bundle.zip
$ sudo ./awscli-bundle/install -i /usr/local/aws -b /usr/local/bin/aws
```

### To send a new message to SQS via aws-cli:

```bash
$ aws sqs send-message --queue-url https://sqs.us-east-1.amazonaws.com/669856304367/dev-email --message-body '{"email_to": "alexandre.gama@elo7.com", "subject": "Welcome", "content": "Welcome to Elo7"}' --message-attributes '{"className": {"StringValue": "com.mailler.Email", "DataType": "String"}, "contentType": {"StringValue": "application/json", "DataType": "String"}}'
```

### To send a new message to SQS Query API

```bash
POST https://sqs.us-east-1.amazonaws.com/669856304367/dev-email?Action=SendMessage
&MessageBody={"email_to": "alexandre.gama@elo7.com", "subject": "Welcome", "content": "Welcome to Elo7"}
&MessageAttribute.1.Name=className
&MessageAttribute.1.Value.StringValue=com.mailler.Email
&MessageAttribute.1.Value.DataType=String
&MessageAttribute.2.Name=contentType
&MessageAttribute.2.Value.StringValue=application/json
&MessageAttribute.2.Value.DataType=String
&AUTHPARAMS
```
