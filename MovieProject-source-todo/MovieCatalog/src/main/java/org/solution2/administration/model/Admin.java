package org.solution2.administration.model;

public class Admin {
    private String login;
    private String pass;


    public Admin(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }
    public Admin() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
