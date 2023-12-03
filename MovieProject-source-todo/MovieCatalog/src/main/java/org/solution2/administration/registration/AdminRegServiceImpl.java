package org.solution2.administration.registration;

import org.solution2.administration.adminValidator.AdminRegValidator;
import org.solution2.administration.model.Admin;

public class AdminRegServiceImpl implements AdminRegService {

    private AdminRegInDB adminRegInDB;
    private AdminRegValidator adminRegValidator;

    public AdminRegServiceImpl(AdminRegInDB adminRegInDB, AdminRegValidator adminRegValidator) {
        this.adminRegInDB = adminRegInDB;
        this.adminRegValidator = adminRegValidator;
    }

    @Override
    public boolean register(Admin admin) {
        try{
            if(adminRegValidator.isValid(admin)){
                adminRegInDB.create(admin);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
