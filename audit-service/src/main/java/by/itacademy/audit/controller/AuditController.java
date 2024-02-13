package by.itacademy.audit.controller;


import by.itacademy.audit.core.dto.AuditDTOWithUser;
import by.itacademy.audit.core.dto.PageDTOCustom;
import by.itacademy.audit.service.api.IAuditService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
public class AuditController {

    private final IAuditService auditService;

    public AuditController(@Qualifier("auditService") IAuditService auditService) {
        this.auditService = auditService;
    }


    @GetMapping("/audit/")
    @Secured("ADMIN")
    public ResponseEntity<PageDTOCustom<AuditDTOWithUser>> getAuditPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                        @RequestParam(value = "size", defaultValue = "20") Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<AuditDTOWithUser> auditWithUserAll = auditService.findAuditWithUserAll(pageRequest);

        PageDTOCustom<AuditDTOWithUser> auditDTOWithUserPageDTOCustom = new PageDTOCustom<>();

        auditDTOWithUserPageDTOCustom.convert(auditWithUserAll);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(auditDTOWithUserPageDTOCustom);

    }

    @GetMapping("/audit/{id}")
    @Secured("ADMIN")
    public ResponseEntity<AuditDTOWithUser> getAuditPageById(@PathVariable String id) {

        Optional<AuditDTOWithUser> auditWithUserByUuid = auditService.findAuditWithUserByUuid(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(auditWithUserByUuid.orElseThrow(() -> new RuntimeException("Not exist exception")));

    }


}
