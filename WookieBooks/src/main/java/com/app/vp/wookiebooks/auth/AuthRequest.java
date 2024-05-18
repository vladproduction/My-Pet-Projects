package com.app.vp.wookiebooks.auth;

/**
 * This class is responsible for:
 * -creating the model of AuthRequest 'request';
 * -has username and password;
 * -using for matching with existing user in db (which has as well: name and password)
 * */
public class AuthRequest {

    private String username;
    private String password;

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
