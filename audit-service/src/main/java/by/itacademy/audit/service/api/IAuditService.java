package by.itacademy.audit.service.api;

import by.itacademy.audit.core.dto.AuditDTO;
import by.itacademy.audit.core.dto.AuditDTOWithUser;
import by.itacademy.audit.repository.entity.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IAuditService {

    boolean existsById(String s);
    AuditEntity saveAndFlush(AuditDTO AuditDTO);

    Optional<AuditEntity> findById(String uuid);

    Page<AuditDTOWithUser> findAuditWithUserAll(Pageable pageable);

    Optional<AuditDTOWithUser> findAuditWithUserByUuid(@Param("uuid") String uuid);
}
