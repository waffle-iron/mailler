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
