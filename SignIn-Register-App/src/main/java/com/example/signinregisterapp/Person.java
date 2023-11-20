package com.example.signinregisterapp;

public class Person {

    private String login;
    private String password;
    private String email;

    public Person(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public Person() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person: [login:" + login + "; password:" + password + "; email:" + email+"]";
    }
}
