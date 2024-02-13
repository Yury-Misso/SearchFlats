package by.itacademy.mail.service.impl;

import by.itacademy.mail.core.dto.EmailVerificationCodeDTO;
import by.itacademy.mail.service.api.IRabbitMQServiceRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceRequest implements IRabbitMQServiceRequest {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQServiceRequest(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendRequest(EmailVerificationCodeDTO emailVerificationCodeDTO) {
        rabbitTemplate.convertAndSend("flats", "missoRequest", emailVerificationCodeDTO);
    }
}
