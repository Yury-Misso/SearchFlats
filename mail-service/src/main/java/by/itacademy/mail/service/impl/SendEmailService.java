package by.itacademy.mail.service.impl;

import by.itacademy.mail.core.dto.EmailVerificationCodeDTO;
import by.itacademy.mail.service.api.ISendEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class SendEmailService implements ISendEmailService {

    private final JavaMailSender javaMailSender;
    private final String verificationServerAddress;

    @Value("${spring.mail.username}")
    private String userName;

    public SendEmailService(JavaMailSender javaMailSender, String verificationServerAddress) {
        this.javaMailSender = javaMailSender;
        this.verificationServerAddress = verificationServerAddress;
    }

    @Override
    @Async
    public void sendVerificationCodeToEmail(EmailVerificationCodeDTO emailVerificationCodeDTO) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(userName);
            helper.setTo(emailVerificationCodeDTO.getEmail());
            helper.setSubject("Your verification code");

            String param = "?code=" + emailVerificationCodeDTO.getVerificationCode()
                    + "&mail=" + emailVerificationCodeDTO.getEmail();


            String s = "<a href=\"" +
                    verificationServerAddress + param + "\">" +
                    emailVerificationCodeDTO.getVerificationCode() +
                    "</a > ";

            helper.setText(s, true);
            javaMailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
