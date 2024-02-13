package by.itacademy.user.service.impl;

import by.itacademy.user.aop.dto.AuditDTO;
import by.itacademy.user.service.api.IRabbitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitService implements IRabbitService {

    private final RabbitTemplate rabbitTemplate;

    public RabbitService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(AuditDTO auditDTO) {

        ObjectMapper objectMapper = new ObjectMapper();

        String message;

        try {
            message = objectMapper.writeValueAsString(auditDTO);
            rabbitTemplate.convertAndSend("flats", "userAudit", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
