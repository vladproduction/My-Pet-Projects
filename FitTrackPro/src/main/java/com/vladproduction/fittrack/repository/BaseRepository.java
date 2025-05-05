package com.vladproduction.fittrack.repository;

import com.vladproduction.fittrack.datasource.CustomStatementCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public abstract class BaseRepository {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected final DataSource dataSource;
    protected final CustomStatementCache statementCache;

    public BaseRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.statementCache = new CustomStatementCache(dataSource);
    }

    public void beginTransaction() {
        try{
            statementCache.getConnection().setAutoCommit(false);
        }catch (Exception e){
            logger.error("Failed to begin transaction: {}", e.getMessage());
        }
    }

    public void commit(){
        try{
            statementCache.getConnection().commit();
        }catch (Exception e){
            logger.error("Failed to commit transaction: {}", e.getMessage());
        }
    }

    public void rollback(){
        try{
            statementCache.getConnection().rollback();
        }catch (Exception e){
            logger.error("Failed to rollback transaction: {}", e.getMessage());
        }
    }

    public void close(){
        try{
            statementCache.close();
        }catch (Exception e){
            logger.error("Failed to close resources: {}", e.getMessage());
        }
    }


}
