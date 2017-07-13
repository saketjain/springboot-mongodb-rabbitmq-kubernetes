package com.sapient.gm.mtl.trade.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sjai30 on 7/2/2017.
 */

@Configuration
@ComponentScan(basePackages = {"com.sapient.gm.mtl"})
@EnableRabbit
public class ApplicationConfig {

    @Bean
    Queue tradeCreateQueue() {
        return new Queue("mtl.trade.create", false);
    }

    @Bean
    DirectExchange tradeCommandExchange() {
        return new DirectExchange("mtl.trade.exchange");
    }

    @Bean
    Binding tradeCreateBinding(Queue tradeCreateQueue, DirectExchange tradeCommandExchange) {
        return BindingBuilder.bind(tradeCreateQueue).to(tradeCommandExchange).with("mtl.trade.create");
    }

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        System.out.println("host===" + connectionFactory.getHost() + " port===" + connectionFactory.getPort() + connectionFactory.getUsername());
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

}
