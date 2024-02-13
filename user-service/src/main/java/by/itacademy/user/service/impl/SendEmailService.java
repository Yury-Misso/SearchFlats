package by.itacademy.user.service.impl;

import by.itacademy.user.core.dto.EmailVerificationCodeDTO;
import by.itacademy.user.service.api.ISendEmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService implements ISendEmailService {

    private final RabbitMQServiceRequest rabbitTemplate;

    public SendEmailService(RabbitMQServiceRequest rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendVerificationCodeToEmail(EmailVerificationCodeDTO emailVerificationCodeDTO) {

//        rabbitTemplate.sendRequest(message.toString());

    }
}
