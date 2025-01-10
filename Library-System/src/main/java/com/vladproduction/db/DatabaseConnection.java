package com.vladproduction.db;

import com.vladproduction.library.utils.PropertiesUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final PropertiesUtils propertiesUtil = new PropertiesUtils();

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USERNAME = propertiesUtil.getProperty("db.username");
    private static final String PASSWORD = propertiesUtil.getProperty("db.password");

    public static Connection getConnection() throws SQLException {
        // First, connect to the server without a specific database
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // Create the library database if it does not exist
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS library");
        } catch (SQLException e) {
            System.err.println("Error creating database: " + e.getMessage());
        }

        // Now, connect to the 'library' database
        return DriverManager.getConnection(URL + "library", USERNAME, PASSWORD);
    }
}
