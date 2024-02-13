package by.itacademy.report.service.impl;

import by.itacademy.report.core.dto.AuditDTO;
import by.itacademy.report.core.dto.AuditDTOWithUser;
import by.itacademy.report.exception.EntityExistExeption;
import by.itacademy.report.repository.AuditRepository;
import by.itacademy.report.repository.entity.AuditEntity;
import by.itacademy.report.service.api.IAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuditService implements IAuditService {

    private final AuditRepository auditRepository;

    private final Logger logger = LoggerFactory.getLogger(AuditService.class);

    public AuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public boolean existsById(String s) {

        return auditRepository.existsById(s);

    }

    @Override
    @Transactional
    public AuditEntity saveAndFlush(AuditDTO auditDTO) {

        String uuid = UUID.randomUUID().toString();

        if (!existsById(uuid)) {

            AuditEntity auditEntity = new AuditEntity()
                    .setUuid(uuid)
                    .setDt_create(auditDTO.getDt_create())
                    .setUser(auditDTO.getUser())
                    .setText(auditDTO.getText())
                    .setType(auditDTO.getType())
                    .setId(auditDTO.getId());

            auditRepository.saveAndFlush(auditEntity);

            return auditEntity;

        } else {
            throw new EntityExistExeption("Запись существует");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AuditEntity> findById(String uuid) {

        return auditRepository.findById(uuid);

    }
}
