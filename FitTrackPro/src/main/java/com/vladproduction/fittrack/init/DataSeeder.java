package com.vladproduction.fittrack.init;

import com.vladproduction.fittrack.datasource.DBConnection;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.*;

public class DataSeeder {

    private static final Logger LOGGER = getLogger(DataSeeder.class);
    private static final String SEED_DATA_INIT_SQL = "seed-data-init.sql";

    public static void seedData() {
        try(Connection connection = DBConnection.getConnection()){

            InputStream inputStream = getSeedDataStreamBySQL();
            if (inputStream == null) return;

            sqlStatementExecution(inputStream, connection);
            LOGGER.info("✅ Seed data successfully inserted.");

        }catch (Exception e){
            LOGGER.error("❌ Failed to insert seed data: {}", e.getMessage());
        }
    }

    private static InputStream getSeedDataStreamBySQL() {
        InputStream inputStream = DataSeeder.class
                .getClassLoader()
                .getResourceAsStream(DataSeeder.SEED_DATA_INIT_SQL);

        if(inputStream == null){
            LOGGER.error("❌ File: {} not found in resources!", DataSeeder.SEED_DATA_INIT_SQL);
            return null;
        }
        return inputStream;
    }

    private static void sqlStatementExecution(InputStream inputStream, Connection connection) throws SQLException {
        String sql = new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.joining("\n"));

        Statement statement = connection.createStatement();
        for (String query : sql.split(";")) {
            if (!query.isBlank()) {
                statement.execute(query);
            }
        }
    }

}
