package by.itacademy.flats.service;

import by.itacademy.flats.aop.dto.AuditDTO;
import by.itacademy.flats.repository.IRabbitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
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
            rabbitTemplate.convertAndSend("flats", "flatsAudit", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
