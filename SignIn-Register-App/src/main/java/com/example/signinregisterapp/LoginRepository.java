package com.example.signinregisterapp;

public interface LoginRepository {
    public boolean isPresent(String login, String pass);
}
