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

    private final static Logger slf4jLogger = LoggerFactory
            .getLogger(PoolJdbc.class);

    private static final String bd_data = "db.properties";

    private static String name;
    private static String username;
    private static String password;

    private static HikariDataSource pool;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionDB("Where is your MySQL JDBC Driver : "
                    + e.getMessage());
        }
        Properties properties = new Properties();
        InputStream propertiesFile = PoolJdbc.class.getClassLoader()
                .getResourceAsStream(PoolJdbc.bd_data);
        try {
            properties.load(propertiesFile);
        } catch (IOException e) {
            throw new ExceptionDB("Cannot get MySQL connection : "
                    + e.getMessage());
        }

        PoolJdbc.name = properties.getProperty("URL")
                + properties.getProperty("DB_NAME")
                + properties.getProperty("PARAMS");
        PoolJdbc.username = properties.getProperty("USERNAME");
        PoolJdbc.password = properties.getProperty("PASSWORD");

        PoolJdbc.pool = new HikariDataSource();
        PoolJdbc.pool.setJdbcUrl(PoolJdbc.name);
        PoolJdbc.pool.setUsername(PoolJdbc.username);
        PoolJdbc.pool.setPassword(PoolJdbc.password);

        slf4jLogger.info("=========== Pool created.  ===========");
    }

    public Connection getConnection() {
        try {
            slf4jLogger.info("=========== Pool get.  ===========");
            return PoolJdbc.pool.getConnection();
        } catch (SQLException e) {
            throw new ExceptionDB("Not connection with name : " + PoolJdbc.name
                    + " " + e.getMessage());
        }
    }

    public void closeConnection(Connection c) {
        if (c == null) {
            throw new IllegalArgumentException("C sould not be null !");
        }
        try {
            c.close();
            slf4jLogger.info("=========== JDBC destroyed.  ===========");
        } catch (SQLException e) {
            slf4jLogger.error("Deconnection failed! " + e.getMessage());
        }
    }

}
