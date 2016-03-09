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
