package by.itacademy.audit.service;

import by.itacademy.audit.core.dto.AuditDTO;
import by.itacademy.audit.service.api.IAuditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AuditRabbitListener {

    private final IAuditService auditService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    Logger logger = LoggerFactory.getLogger(IAuditService.class);

    public AuditRabbitListener(@Qualifier("auditService") IAuditService auditService) {
        this.auditService = auditService;
    }

    @RabbitListener(queues = "flatsAudit")
    public void flatsListener(String message) {

        processMessage(message);

    }

    @RabbitListener(queues = "userAudit")
    public void userListener(String message) {

        processMessage(message);

    }





    private void processMessage(String message) {

        try {

            AuditDTO auditDTO = objectMapper.readValue(message, AuditDTO.class);

            auditService.saveAndFlush(auditDTO);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
