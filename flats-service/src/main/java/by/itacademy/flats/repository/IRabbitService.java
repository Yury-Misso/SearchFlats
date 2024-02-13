package by.itacademy.flats.repository;

import by.itacademy.flats.aop.dto.AuditDTO;
import org.springframework.stereotype.Service;

@Service
public interface IRabbitService {

    void sendMessage(AuditDTO auditDTO);
}
