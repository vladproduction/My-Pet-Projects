package com.example.signinregisterapp;

public class ValidatorRegisterImpl implements ValidatorRegister {
    @Override
    public boolean isCorrectRegistration(Person person) {
        if(person==null){
            return false;
        }
        String login = person.getLogin();
        if (isEmpty(login) || login.contains("/") || login.length()<4){
            return false;
        }
        String password = person.getPassword();
        if (isEmpty(password) || password.length()<4){
            return false;
        }
        String email = person.getEmail();
        if(isEmpty(email) || email.contains("@@") || email.contains("..") || email.contains("@.")){
            return false;
        }

        return true;
    }

    private boolean isEmpty(String value) {
        if(value==null || value.trim().isEmpty()){
            return true;
        }
        return false;
    }
}

