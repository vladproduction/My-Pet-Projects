package com.example.signinregisterapp;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterRepositoryImpl extends AbstractRepository implements RegisterRepository {

    @Override
    public void create(Person person) {

        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("insert into persontabel" +
                    "(login, password, email) values (?, ?, ?)");
            ps.setString(1, person.getLogin());
            ps.setString(2, person.getPassword());
            ps.setString(3, person.getEmail());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
