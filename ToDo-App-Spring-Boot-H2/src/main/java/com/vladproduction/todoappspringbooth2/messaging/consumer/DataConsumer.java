package com.vladproduction.todoappspringbooth2.messaging.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladproduction.todoappspringbooth2.entity.ItemToDo;
import com.vladproduction.todoappspringbooth2.repository.ItemToDoRepository;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by vladproduction on 12-Mar-24
 */

@Component
public class DataConsumer {

    private ObjectMapper objectMapper;
    private ItemToDoRepository repository;

    public DataConsumer(ObjectMapper objectMapper, ItemToDoRepository repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
    }

    @JmsListener(destination = "todo-app-queue")
    public void onMessage(Message message) throws JMSException {
        System.out.println("RECEIVED: " + message);
        if(message instanceof TextMessage){
            TextMessage textMessage = (TextMessage)message;
            String contentText = textMessage.getText();
            ItemToDo transformedText = transformText(contentText);
            repository.save(transformedText);
        }
    }

    private ItemToDo transformText(String contentText) {
        try {
            return objectMapper.readValue(contentText, ItemToDo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
