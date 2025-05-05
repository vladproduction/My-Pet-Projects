package com.vladproduction.fittrack.init;

import com.vladproduction.fittrack.datasource.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SchemaInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchemaInitializer.class);
    public static final String SCHEMA_SQL = "schema.sql";

    public static void main(String[] args) throws InterruptedException {
        LOGGER.info("Initializing database schema...");
        initializeSchema();

        LOGGER.info("Seeding data...");
        TimeUnit.SECONDS.sleep(5);
        DataSeeder.seedData();
    }

    private static void initializeSchema() {
        try (Connection connection = DBConnection.getConnection()) {

            InputStream inputStream = getSchemaInputStream();

            sqlStatementExecution(inputStream, connection);

        } catch (Exception e) {
            LOGGER.error("❌ Failed to initialize database schema: {}", e.getMessage());
        }
    }

    private static InputStream getSchemaInputStream() {
        InputStream inputStream = SchemaInitializer.class
                .getClassLoader()
                .getResourceAsStream(SCHEMA_SQL);

        if (inputStream == null) {
            LOGGER.error("❌ schema.sql file not found in resources!");
        }
        return inputStream;
    }

    private static void sqlStatementExecution(InputStream inputStream, Connection connection) throws SQLException {
        String sql = null;

        if(inputStream != null){
            sql = new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .collect(Collectors.joining("\n"));
        }

        Statement statement = connection.createStatement();
        if(sql != null){
            for (String query : sql.split(";")) {
                if(!query.isBlank()){
                    statement.execute(query);
                }
            }
            LOGGER.info("✅ Database schema initialized.");
        }
    }


}
