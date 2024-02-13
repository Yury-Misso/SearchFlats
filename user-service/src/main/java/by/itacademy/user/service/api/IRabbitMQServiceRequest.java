package by.itacademy.user.service.api;

import by.itacademy.user.core.dto.EmailVerificationCodeDTO;
import org.springframework.stereotype.Service;

@Service
public interface IRabbitMQServiceRequest {
    void sendRequest(EmailVerificationCodeDTO emailVerificationCodeDTO);

}
