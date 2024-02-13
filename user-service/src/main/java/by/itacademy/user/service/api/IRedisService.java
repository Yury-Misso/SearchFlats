package by.itacademy.user.service.api;

import by.itacademy.user.core.dto.EmailVerificationCodeDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IRedisService {

    void save(EmailVerificationCodeDTO emailVerificationCodeDTO);

    void delete(String eMail);

    UUID getValue(String key);

    boolean exist(String eMail);
}
