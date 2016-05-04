package com.excilys.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import com.excilys.database.VirtualJdbc;

public class ThreadJdbc implements VirtualJdbc {
    
    private static Connection connect;
    private static Class<?> dbDriver = null;
    private static final String DB_DATA = "db.properties";
    
    private static String name;
    private static String username;
    private static String password; 
    
    static {
        try {
            dbDriver = Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionDB("Where is your MySQL JDBC Driver? : " +  e.getMessage());
        }
        
        
        Properties properties = new Properties();
        InputStream propertiesFile = PoolJdbc.class.getClassLoader()
                .getResourceAsStream(DB_DATA);
        try {
            properties.load(propertiesFile);
        } catch (IOException e) {
            throw new ExceptionDB("Cannot get MySQL connection file : "
                    + e.getMessage());
        }
        name = properties.getProperty("URL")
                + properties.getProperty("DB_NAME")
                + properties.getProperty("PARAMS");
        username = properties.getProperty("USERNAME");
        password = properties.getProperty("PASSWORD");
    }
    
    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Connection> threadId = new ThreadLocal<Connection>() {
        @Override
        protected Connection initialValue() {

            
           
            try {
                connect = DriverManager.getConnection(name, username, password);
            } catch (SQLException e) {
                throw new ExceptionDB("Connection Failed! " +  e.getMessage());
            }
            return connect;
        }
    };

    @Override
    public Connection getConnection() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void closeConnection(Connection c) {
        // TODO Auto-generated method stub
        
    }


}
