package by.itacademy.user.service.api;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface IRabbitMQServiceResponse {

    void receiveResponse(String message);

}
