package by.itacademy.mail.service.api;

import by.itacademy.mail.core.dto.EmailVerificationCodeDTO;
import org.springframework.stereotype.Service;

@Service
public interface ISendEmailService {

    void sendVerificationCodeToEmail(EmailVerificationCodeDTO emailVerificationCodeDTO);

}
