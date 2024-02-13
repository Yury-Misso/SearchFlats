package by.itacademy.mail.service.api;

import org.springframework.stereotype.Component;

@Component
public interface IRabbitMQServiceResponse {

    void receiveResponse(String message);

}
