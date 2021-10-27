# spring-boot-mail-sender-and-listener



---

This Project using SMTP for Mail Sender and Artemis for Mail Listener
## Requirements

- Spring Boot
- SMTP
- ActiveMQ Artemiis
- Docker
- Docker-Compose
- SpringDoc(Swagger)


## Usage

```java 

 //Editing your mail settings
spring.mail.host=smtp.gmail.com
spring.mail.protocol=smtp
spring.mail.username=
spring.mail.password=
spring.mail.port=587
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.main.allow-bean-definition-overriding=true

//Editing your artemis settings
spring.artemis.mode=native
spring.artemis.host=localhost
spring.artemis.port=61616
spring.artemis.user=admin
spring.artemis.password=password

jms.queue=Q.Mail

```

## License
[Muharrem Koç](https://github.com/muharremkoc)
