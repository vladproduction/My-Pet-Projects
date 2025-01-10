package com.vladproduction.library.app;

import com.vladproduction.db.DatabaseConnection;
import com.vladproduction.db.DatabaseInitializer;

import java.sql.Connection;

public class LibraryApp {
    public static void main(String[] args) {
        System.out.println("Hello Library App");
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Initialize the database
            DatabaseInitializer.initializeDatabase(connection);
            System.out.println("Now we can use our LibraryService here...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
