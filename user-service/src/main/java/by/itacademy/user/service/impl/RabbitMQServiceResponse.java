package by.itacademy.user.service.impl;

import by.itacademy.user.service.api.IRabbitMQServiceResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQServiceResponse implements IRabbitMQServiceResponse {


    public RabbitMQServiceResponse() {

    }


    @Override
    @RabbitListener(queues = "responseQueueEmail")
    public void receiveResponse(String message) {

    }
}
