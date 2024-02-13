package by.itacademy.mail.service.api;

import by.itacademy.mail.core.dto.EmailVerificationCodeDTO;
import org.springframework.stereotype.Service;

@Service
public interface IRabbitMQServiceRequest {

    void sendRequest(EmailVerificationCodeDTO emailVerificationCodeDTO);

}
