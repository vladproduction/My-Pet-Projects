package org.solution2.administration.registration;

import org.solution2.administration.model.Admin;
import org.solution2.commonUtily.AbstractConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AdminRegInDBImpl extends AbstractConnection implements AdminRegInDB {
    @Override
    public void create(Admin admin) {

        try(Connection connection = getConnection()){
            PreparedStatement ps = connection.prepareStatement("insert into admins (Login, Password) values (?, ?)");
            ps.setString(1, admin.getLogin());
            ps.setString(2, admin.getPass());
            ps.execute();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
}
