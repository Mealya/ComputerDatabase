package com.excilys.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariDataSource;

public class PoolJdbc implements VirtualJdbc {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(PoolJdbc.class);

    private static final String DB_DATA = "db.properties";

    private static String name;
    private static String username;
    private static String password; 

    private static HikariDataSource pool;

    static {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Fail to get driver sql" + e.getMessage());
            throw new ExceptionDB("Where is your MySQL JDBC Driver : "
                    + e.getMessage());
        }
        Properties properties = new Properties();
        InputStream propertiesFile = PoolJdbc.class.getClassLoader()
                .getResourceAsStream(DB_DATA);
        try {
            properties.load(propertiesFile);
        } catch (IOException e) {
            throw new ExceptionDB("Cannot get MySQL connection : "
                    + e.getMessage());
        }

        name = properties.getProperty("URL")
                + properties.getProperty("DB_NAME")
                + properties.getProperty("PARAMS");
        username = properties.getProperty("USERNAME");
        password = properties.getProperty("PASSWORD");

        pool = new HikariDataSource();
        pool.setJdbcUrl(name);
        pool.setUsername(username);
        pool.setPassword(password);
        
        pool.addDataSourceProperty("serverTimeZone", "UTC+1");
        pool.addDataSourceProperty("zeroDateTimeBehavior", "convertToNull");
        pool.addDataSourceProperty("useSSL", "true");
        pool.addDataSourceProperty("useUnicode", "true");
        pool.addDataSourceProperty("characterEncoding", "UTF-8");
        pool.addDataSourceProperty("cachePrepStmts", "true");
        pool.addDataSourceProperty("prepStmtCacheSize", "250");
        pool.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        LOGGER.debug("=========== Pool created.  ===========");
    }

    @Override
    public Connection getConnection() {
        try {
            LOGGER.debug("=========== Pool get.  ===========");
            return pool.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Fail to get a connection " + e.getMessage());
            throw new ExceptionDB("Not connection with name : " + PoolJdbc.name
                    + " " + e.getMessage());
        }
    }

    @Override
    public void closeConnection(Connection c) {
        if (c == null) {
            throw new IllegalArgumentException("C sould not be null !");
        }
        try {
            c.close();
            LOGGER.debug("=========== Pool close connection.  ===========");
        } catch (SQLException e) {
            LOGGER.error("Deconnection failed! " + e.getMessage());
            throw new ExceptionDB(e.getMessage());
        }
    }

    public void rollBack(Connection c) {
        try {
            c.rollback();
        } catch (SQLException e) {
            LOGGER.error("RollBack fail! " + e.getMessage());
            throw new ExceptionDB(e.getMessage());
        }
    }
}
