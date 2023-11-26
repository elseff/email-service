package ru.elseff.emailservice.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elseff.emailservice.model.EmailDetails;
import ru.elseff.emailservice.service.EmailService;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailController {

    EmailService emailService;

    @PostMapping("/send")
    public String sendMail(@RequestBody @Valid EmailDetails details) {
        return emailService.sendSimpleEmail(details);
    }

    @PostMapping("/send-with-attachment")
    public String sendEmailWithAttachment(@RequestBody @Valid EmailDetails details) {
        return emailService.sendEmailWithAttachment(details);
    }


}
