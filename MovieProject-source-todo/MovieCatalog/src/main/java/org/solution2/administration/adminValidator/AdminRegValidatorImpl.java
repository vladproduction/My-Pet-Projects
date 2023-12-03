package org.solution2.administration.adminValidator;

import org.solution2.administration.model.Admin;

public class AdminRegValidatorImpl implements AdminRegValidator {
    @Override
    public boolean isValid(Admin admin) {
        if(admin==null) return false;

        String login = admin.getLogin();
        if (isEmpty(login)) return false;

        String pass = admin.getPass();
        if(isEmpty(pass)) return false;

        return true;
    }

    private boolean isEmpty(String s){
        if (s==null || s.trim().isEmpty()) return true;
        return false;
    }


}
