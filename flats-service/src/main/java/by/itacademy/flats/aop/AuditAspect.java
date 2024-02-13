package by.itacademy.flats.aop;


import by.itacademy.flats.aop.dto.AuditDTO;
import by.itacademy.flats.core.dto.UserDetailsDTO;
import by.itacademy.flats.repository.IRabbitService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

    private final IRabbitService rabbitService;

    private Logger logger = LoggerFactory.getLogger(AuditAspect.class);

    public AuditAspect(@Qualifier("rabbitService") IRabbitService rabbitService) {
        this.rabbitService = rabbitService;
    }

    @Around("@annotation(Audit)")
    public Object doAudit(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Audit annotation = signature.getMethod().getAnnotation(Audit.class);

        Object proceed = joinPoint.proceed();

        UserDetailsDTO principal = (UserDetailsDTO) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        AuditDTO auditDTO = new AuditDTO()
                .setDt_create(System.currentTimeMillis())
                .setType(annotation.essenceType().name())
                .setText(annotation.text().getDescription())
                .setUser(principal.getUuid())
                .setId(principal.getUuid());

        rabbitService.sendMessage(auditDTO);

        return proceed;
    }


}
