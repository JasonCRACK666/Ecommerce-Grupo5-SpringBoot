package com.group5.ecommerce.utils.mails;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendEmailsUtils extends EmailBodies {

    private final JavaMailSender javaMailSender;

    public void sendAccountActivationEmail(
            String to,
            String activationCode
    ) {
        var subject = "Activación de cuenta - TechHouse";

        SimpleMailMessage message = this.sendEmail(
                to,
                subject
        );

        var url = "https://techhouse.com/auth/activate/" + activationCode;

        message.setText(this.activationAccountBody(url));

        javaMailSender.send(message);
    }

    private SimpleMailMessage sendEmail(
            String to,
            String subject
    ) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("ecommerce_api_official@gmail.com");
        message.setTo(to);
        message.setSubject(subject);

        return message;
    }

}
