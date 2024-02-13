package by.itacademy.report.config;

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
    TopicExchange exchange() {
        return new TopicExchange("flats");
    }


    @Bean
    Queue reportQueue() {
        return new Queue("reportQueue", false);
    }

    @Bean
    Binding bindingUser(Queue reportQueue, TopicExchange exchange) {
        return BindingBuilder.bind(reportQueue).to(exchange).with("reportQueue");
    }

}

