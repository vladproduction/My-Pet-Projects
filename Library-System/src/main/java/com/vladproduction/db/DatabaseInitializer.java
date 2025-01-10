package com.vladproduction.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initializeDatabase(Connection connection) {
        // Path for creating database
        executeSqlScript(connection, "create_database.sql");
        // Path for creating tables
        executeSqlScript(connection, "create_tables.sql");
    }

    private static void executeSqlScript(Connection connection, String script) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                DatabaseInitializer.class.getClassLoader().getResourceAsStream(script)));
             Statement statement = connection.createStatement()) {

            StringBuilder sql = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                // Ignore comments
                if (line.trim().startsWith("--")) continue;

                // Append line to SQL script
                sql.append(line).append("\n");

                // If we reach the end of a statement, execute it
                if (line.trim().endsWith(";")) {
                    statement.executeUpdate(sql.toString());
                    sql.setLength(0); // Clear the StringBuilder for the next statement
                }
            }

            System.out.println("Database initialized successfully.");
        } catch (IOException e) {
            System.err.println("Error reading SQL script: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error executing SQL script: " + e.getMessage());
        }
    }
}
