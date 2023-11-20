package com.example.signinregisterapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractRepository {

    Connection getConnection() throws SQLException {
        String url = ConfigUtils.getConfigProperty("DB.url");
        String login = ConfigUtils.getConfigProperty("DB.login");
        String password = ConfigUtils.getConfigProperty("DB.password");
        return DriverManager.getConnection(url,login,password);
    }
}

/*DB.url=jdbc:mysql://localhost:3306/signin-register-app
DB.login=root
DB.password=11111111*/
