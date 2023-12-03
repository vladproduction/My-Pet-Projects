package org.solution2.administration;

import org.solution2.administration.adminValidator.AdminRegValidator;
import org.solution2.administration.adminValidator.AdminRegValidatorImpl;
import org.solution2.administration.model.Admin;
import org.solution2.administration.registration.AdminRegInDB;
import org.solution2.administration.registration.AdminRegInDBImpl;
import org.solution2.administration.registration.AdminRegService;
import org.solution2.administration.registration.AdminRegServiceImpl;

public class AdminTestMain01 {
    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.setLogin("admin01");
        admin.setPass("passtest01");
        AdminRegInDB reg = new AdminRegInDBImpl();
        AdminRegValidator validator = new AdminRegValidatorImpl();
        AdminRegService service = new AdminRegServiceImpl(reg,validator);
        boolean is = service.register(admin);
        System.out.println(is);

    }
}
