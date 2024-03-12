package com.vladproduction.todoappspringbooth2.configuration;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

/**
 * Created by vladproduction on 11-Mar-24
 */

@Configuration
@EnableJms
public class JMSConfig {

    @Value("${destination.name}")
    private String destinationName;

    @Bean
    public ConnectionFactory connectionFactory(){
        return new ActiveMQConnectionFactory();
    }

    @Bean
    public Destination destination(){
        return new ActiveMQQueue(destinationName);
    }

}
