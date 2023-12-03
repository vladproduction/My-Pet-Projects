package org.solution2.administration.enter;

import org.solution2.commonUtily.AbstractConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminEnterInDBImpl extends AbstractConnection implements AdminEnterInDB {
    @Override
    public boolean isPresent(String login, String pass) {
        try (Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement("select count(*) FROM admins where " +
                    "Login = ? and Password = ?");
            ps.setString(1, login);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count==1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
