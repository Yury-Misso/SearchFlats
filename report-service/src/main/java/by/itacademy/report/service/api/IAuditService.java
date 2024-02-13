package by.itacademy.report.service.api;

import by.itacademy.report.core.dto.AuditDTO;
import by.itacademy.report.core.dto.AuditDTOWithUser;
import by.itacademy.report.repository.entity.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public interface IAuditService {

    boolean existsById(String s);
    AuditEntity saveAndFlush(AuditDTO AuditDTO);

    Optional<AuditEntity> findById(String uuid);


}
