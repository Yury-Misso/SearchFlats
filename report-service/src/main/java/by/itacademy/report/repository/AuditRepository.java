package by.itacademy.report.repository;

import by.itacademy.report.core.dto.AuditDTOWithUser;
import by.itacademy.report.repository.entity.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface AuditRepository extends JpaRepository<AuditEntity, String> {

    @Override
    boolean existsById(String s);

    @Override
    AuditEntity saveAndFlush(AuditEntity auditEntity);

    @Override
    List<AuditEntity> findAll();

    @Override
    Optional<AuditEntity> findById(String uuid);


}
