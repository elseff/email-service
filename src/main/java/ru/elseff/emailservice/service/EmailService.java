package ru.elseff.emailservice.service;

import ru.elseff.emailservice.model.EmailDetails;

public interface EmailService {

    String sendSimpleEmail(EmailDetails details);

    String sendEmailWithAttachment(EmailDetails details);
}
