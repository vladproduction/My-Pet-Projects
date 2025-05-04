package com.vladproduction.fittrack.repository.base;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomStatementCache {

    private final DataSource dataSource;
    private final Map<String, PreparedStatement> statementCache = new HashMap<>();
    private Connection cachedConnection;

    public CustomStatementCache(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public PreparedStatement preparedStatement(String sql) throws SQLException {
        if(cachedConnection == null || cachedConnection.isClosed()){
            cachedConnection = dataSource.getConnection();
            statementCache.clear();
        }
        return statementCache.computeIfAbsent(sql, s ->{
            try{
                return cachedConnection.prepareStatement(s);
            }catch (SQLException e){
                throw new RuntimeException("Failed to prepare statement: " + s, e);
            }
        });
    }

    public Connection getConnection() {
        return cachedConnection;
    }

    public void close() throws SQLException {
        try{
            for (PreparedStatement stmt : statementCache.values()) {
                stmt.close();
            }
            if (cachedConnection != null && !cachedConnection.isClosed()) {
                cachedConnection.close();
            }
        }catch (SQLException e){
            throw new RuntimeException("Failed to close resources", e);
        }finally {
            statementCache.clear();
            cachedConnection = null;
        }
    }

}















