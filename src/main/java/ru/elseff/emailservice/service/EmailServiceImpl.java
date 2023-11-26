package ru.elseff.emailservice.service;

import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.elseff.emailservice.model.EmailDetails;

import java.io.File;
import java.util.Objects;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailServiceImpl implements EmailService {

    final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String sender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public String sendSimpleEmail(EmailDetails details) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(details.getRecipient());
            message.setText(details.getMessage());
            message.setSubject(details.getSubject());

            javaMailSender.send(message);
            return "Mail sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while sending email";
        }
    }

    @Override
    public String sendEmailWithAttachment(EmailDetails details) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(message, true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setText(details.getMessage());
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setSubject(details.getSubject());

            FileSystemResource file = new FileSystemResource(
                    new File(details.getAttachment())
            );

            mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

            javaMailSender.send(message);
            return "Mail sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while sending email";
        }
    }
}
