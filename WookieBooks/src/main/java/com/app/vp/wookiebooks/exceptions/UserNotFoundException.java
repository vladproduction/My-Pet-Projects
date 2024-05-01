package com.app.vp.wookiebooks.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        System.out.println("UserNotFound  by current Id");
    }
}
