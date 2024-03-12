package com.vladproduction.todoappspringbooth2.messaging.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladproduction.todoappspringbooth2.entity.ItemToDo;
import jakarta.jms.*;
import org.springframework.stereotype.Component;

/**
 * Created by vladproduction on 12-Mar-24
 */

@Component
public class DataProducer {

    private ConnectionFactory connectionFactory;
    private Destination destination;
    private ObjectMapper objectMapper;

    public DataProducer(ConnectionFactory connectionFactory, Destination destination, ObjectMapper objectMapper) {
        this.connectionFactory = connectionFactory;
        this.destination = destination;
        this.objectMapper = objectMapper;
    }

    public void send(ItemToDo itemToDo){
        try (Connection connection = connectionFactory.createConnection()){
            Session session = connection.createSession();
            MessageProducer producer = session.createProducer(destination);
            String content = transform(itemToDo);
            TextMessage message = session.createTextMessage();
            message.setText(content);
            producer.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private String transform(ItemToDo itemToDo) throws JsonProcessingException {
        return objectMapper.writeValueAsString(itemToDo);
    }
}
