package by.itacademy.audit.repository;

import by.itacademy.audit.core.dto.AuditDTOWithUser;
import by.itacademy.audit.repository.entity.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

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

    @Query("SELECT new by.itacademy.audit.core.dto.AuditDTOWithUser(a.uuid, a.dt_create, new by.itacademy.audit.core.dto.AuditDTOWithUser$UserDTO(u.uuid, u.mail, u.fio, u.role), a.text, a.type, a.id) FROM AuditEntity a JOIN UserEntity u ON a.user_id = u.uuid")
    Page<AuditDTOWithUser> findAuditWithUserAll(Pageable pageable);

    @Query("SELECT new by.itacademy.audit.core.dto.AuditDTOWithUser(a.uuid, a.dt_create, new by.itacademy.audit.core.dto.AuditDTOWithUser$UserDTO(u.uuid, u.mail, u.fio, u.role), a.text, a.type, a.id) FROM AuditEntity a JOIN UserEntity u ON a.user_id = u.uuid WHERE a.uuid = :uuid")
    Optional<AuditDTOWithUser> findAuditWithUserByUuid(@Param("uuid") String uuid);


}
