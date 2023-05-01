package com.group5.ecommerce.utils.mails;

import com.group5.ecommerce.exception.MailNotSentException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import lombok.RequiredArgsConstructor;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SendEmailsUtils extends EmailBodies {

    private final JavaMailSender javaMailSender;

    public void sendAccountActivationEmail(
            String to,
            UUID activationCode
    ) throws MailNotSentException {
        var subject = "Activación de cuenta - TechHouse";

        try {
            MimeMessage message = this.sendEmail(to, subject);

            message.setContent(this.activationAccountBody(activationCode), "text/html; charset=utf-8");

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new MailNotSentException("El correo de activación no ha podido ser enviado");
        }
    }

    private MimeMessage sendEmail(
            String to,
            String subject
    ) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setFrom("ecommerce_api_official@gmail.com");
        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject(subject);

        return message;
    }

}
