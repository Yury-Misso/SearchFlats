package by.itacademy.user.aop;


import by.itacademy.user.aop.dto.AuditDTO;
import by.itacademy.user.core.dto.UserDTO;
import by.itacademy.user.core.dto.UserDetailsDTO;
import by.itacademy.user.core.dto.UserLoginDTO;
import by.itacademy.user.service.api.IRabbitService;
import by.itacademy.user.service.api.IUserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class AuditAspect {

    private final IRabbitService rabbitService;
    private final IUserService userService;

    private Logger logger = LoggerFactory.getLogger(AuditAspect.class);

    public AuditAspect(@Qualifier("rabbitService") IRabbitService rabbitService,
                       @Qualifier("userService") IUserService userService) {
        this.rabbitService = rabbitService;
        this.userService = userService;
    }

    @Around("@annotation(Audit)")
    public Object doAudit(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Audit annotation = signature.getMethod().getAnnotation(Audit.class);
        UserDetailsDTO principal = getPrincipal();

        Object proceed = joinPoint.proceed();

        switch (annotation.text()) {
            case CREATE_USER, UPDATE_USER -> {
                UserDTO userDTO = (UserDTO) proceed;
                createAuditDTOAndSend(annotation, userDTO.getUuid(), principal);
            }
            case VERIFICATION -> {
                String eMail = (String) joinPoint.getArgs()[0];
                Optional<UserDTO> byMail = userService.findByMail(eMail);
                createAuditDTOAndSend(annotation, byMail.get().getUuid(), principal);
            }
            case LOGIN -> {
                UserLoginDTO userLoginDTO = (UserLoginDTO) joinPoint.getArgs()[0];
                Optional<UserDTO> byMail = userService.findByMail(userLoginDTO.getMail());
                if (byMail.isPresent()) {
                    createAuditDTOAndSend(annotation, byMail.get().getUuid(), principal);
                } else {
                    createAuditDTOAndSend(annotation, principal.getUuid(), principal);
                }
            }
            case ABOUT_ME, ABOUT_ALL -> {
                createAuditDTOAndSend(annotation, principal.getUuid(), principal);
            }
            case ABOUT_USER -> {
                String id = (String) joinPoint.getArgs()[0];
                createAuditDTOAndSend(annotation, id, principal);
            }
            default -> {
                throw new RuntimeException("Error " + annotation.text());
            }

        }

        return proceed;
    }

    private void createAuditDTOAndSend(Audit annotation, String id, UserDetailsDTO principal) {

        AuditDTO auditDTO = new AuditDTO()
                .setDt_create(System.currentTimeMillis())
                .setType(annotation.essenceType().name())
                .setText(annotation.text().getDescription())
                .setUser(principal.getUuid())
                .setId(id);

        rabbitService.sendMessage(auditDTO);

    }


    private UserDetailsDTO getPrincipal() {
        try {

            return (UserDetailsDTO) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

        } catch (RuntimeException e) {
            return new UserDetailsDTO("11111111-1111-1111-1111-111111111111", "", "", "USER");
        }

    }


}
