package by.itacademy.flats.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Bean
    public RabbitTemplate getRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        return template;
    }

    @Bean
    Queue flatsQueue() {
        return new Queue("flatsAudit", false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("flats");
    }

    @Bean
    Binding bindingRequest(Queue flatsQueue, TopicExchange exchange) {
        return BindingBuilder.bind(flatsQueue).to(exchange).with("flatsAudit");
    }

}

