package ru.elseff.emailservice.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppConfig {

    @Value("${spring.mail.host}")
    String host;

    @Value("${spring.mail.port}")
    Integer port;

    @Value("${spring.mail.username}")
    String username;

    @Value("${spring.mail.password}")
    String password;

    @Value("${spring.mail.protocol}")
    String mailProtocol;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    Boolean mailSmtpAuth;

    @Value("${spring.mail.properties.mail.debug}")
    Boolean mailDebug;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    Boolean mailSmtpStartTlsEnable;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(username);
        sender.setPassword(password);

        Properties props = sender.getJavaMailProperties();
        props.put("mail.transport.protocol", mailProtocol);
        props.put("mail.smtp.auth", mailSmtpAuth);
        props.put("mail.smtp.starttls.enable", mailSmtpStartTlsEnable);
        props.put("mail.debug", mailDebug);

        return sender;
    }
}
