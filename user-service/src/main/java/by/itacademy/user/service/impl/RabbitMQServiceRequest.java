package by.itacademy.user.service.impl;

import by.itacademy.user.core.dto.EmailVerificationCodeDTO;
import by.itacademy.user.service.api.IRabbitMQServiceRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        ObjectMapper objectMapper = new ObjectMapper();

        String message;

        try {
            message = objectMapper.writeValueAsString(emailVerificationCodeDTO);
            rabbitTemplate.convertAndSend("flats", "missoRequest", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
