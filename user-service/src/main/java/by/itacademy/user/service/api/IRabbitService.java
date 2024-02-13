package by.itacademy.user.service.api;

import by.itacademy.user.aop.dto.AuditDTO;
import org.springframework.stereotype.Service;

@Service
public interface IRabbitService {

    void sendMessage(AuditDTO auditDTO);
}
