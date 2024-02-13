package by.itacademy.mail.service.impl;

import by.itacademy.mail.core.dto.EmailVerificationCodeDTO;
import by.itacademy.mail.service.api.IEmailVerificationService;
import by.itacademy.mail.service.api.ISendEmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmailVerificationService implements IEmailVerificationService {

    private final ISendEmailService iSendEmailService;

    public EmailVerificationService(@Qualifier("sendEmailService") ISendEmailService iSendEmailService) {
        this.iSendEmailService = iSendEmailService;
    }

    @RabbitListener(queues = "requestQueueEmail")
    public void receiveMessage(String message) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            EmailVerificationCodeDTO emailVerificationCodeDTO = objectMapper.readValue(message, EmailVerificationCodeDTO.class);
            iSendEmailService.sendVerificationCodeToEmail(emailVerificationCodeDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
