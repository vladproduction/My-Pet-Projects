package com.example.signinregisterapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginRepositoryImpl extends AbstractRepository implements LoginRepository {
    @Override
    public boolean isPresent(String login, String password) {
        try(Connection c = getConnection()){
            PreparedStatement ps = c.prepareStatement("SELECT count(*) FROM persontabel " +
                    "where login = ? and password = ?");
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count==1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


