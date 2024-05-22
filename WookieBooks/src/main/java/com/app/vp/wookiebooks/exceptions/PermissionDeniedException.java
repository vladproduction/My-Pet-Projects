package com.app.vp.wookiebooks.exceptions;

public class PermissionDeniedException extends RuntimeException{

    public PermissionDeniedException(String message) {
        super(message);
    }
}
