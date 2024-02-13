package by.itacademy.mail.service.impl;

import by.itacademy.mail.service.api.IRabbitMQServiceResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQServiceResponse implements IRabbitMQServiceResponse {


    public RabbitMQServiceResponse() {

    }


    @Override
//    @RabbitListener(queues = "responseQueueEmail")
    public void receiveResponse(String message) {

    }
}
