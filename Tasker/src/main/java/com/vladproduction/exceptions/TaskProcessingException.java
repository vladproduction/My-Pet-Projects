package com.vladproduction.exceptions;

public class TaskProcessingException extends RuntimeException{

    public TaskProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
