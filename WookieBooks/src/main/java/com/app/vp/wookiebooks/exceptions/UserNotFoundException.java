package com.app.vp.wookiebooks.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        System.out.println("User Not Found  by current Id");
    }
}
