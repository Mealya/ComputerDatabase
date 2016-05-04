package com.excilys.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Angot Maxime on 19/04/16.
 * 
 * @author Angot Maxime
 */
public class BasicJdbc implements VirtualJdbc {

    private final Logger LOGGER = LoggerFactory.getLogger(BasicJdbc.class);

    private static Map<String, Connection> connections = new HashMap<String, Connection>();
    private static Class<?> dbDriver = null;
    
    private static final Object LOCK_CREATE = new Object();
    private static final Object LOCK_DELETE = new Object();
    private static final Object LOCK_DRIVER = new Object();
    
    private String name = "computer-database-db";
    
    /**
     * Create an object Connection with a DB name.
     */
    private void linkToMySql() {
        LOGGER.info("=========== MySQL JDBC Connecting.....  ===========");

        try {
            dbDriver = Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Where is your MySQL JDBC Driver? : "
                    + e.getMessage());
            throw new ExceptionDB("Where is your MySQL JDBC Driver? : " +  e.getMessage());
        }
        LOGGER.info("MySQL JDBC Driver Registered!");
    }

    public void connect(String nameDB) {
        if (nameDB == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        synchronized (LOCK_CREATE) {
            if (connections.get(nameDB) != null) {
                return;
            }
        }
        if (dbDriver == null) {
            synchronized (LOCK_DRIVER) {
                if (dbDriver == null) {
                    linkToMySql();
                }
            }
        }
        
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + nameDB
                    + "?zeroDateTimeBehavior=convertToNull",
                    "admincdb", "qwerty1234");

        } catch (SQLException e) {
            LOGGER.error("Connection Failed! " + e.getMessage());

            throw new ExceptionDB("Connection Failed! " +  e.getMessage());
        }

        if (connection != null) {
            connections.put(nameDB, connection);
        } else {
            LOGGER.error("Failed to make connection!");
            throw new ExceptionDB("Failed to make connection !");
        }
        LOGGER.info("=========== MySQL JDBC created.....  ===========");
    }
    
    public void setConnectionName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        this.name = name;
    }
    
    /**
     * Return the connection object linked to a name.
     * 
     * @param name
     *            The name of the database
     * @return Connection object
     */
    public Connection getConnection() {
       
        synchronized (LOCK_DELETE) {
            Connection c = connections.get(name);
            if (c == null) {
                connect(name);
                c = connections.get(name);
            }
            try {
                if (c.isClosed()) {
                    connect(name);
                }
            } catch (SQLException e) {
                LOGGER.error("Failed to make connection! " + e.getMessage());
            }
            return connections.get(name);
        }
         
    }

    /**
     * Close the Connection linked to a name.
     * 
     * @param name
     *            The name of the data base
     */
    public void closeConnect(Connection c) {
        synchronized (LOCK_DELETE) {
            try {
                c.close();
                connections.remove(c);
                LOGGER
                .info("=========== MySQL JDBC destroyed.....  ===========");
            } catch (SQLException e) {
                LOGGER.error("Deconnection failed! "
                        + e.getMessage());
            }
            
            
            /*
            for (Map.Entry<String, Connection> c : connections.entrySet()) {
                if (c.getKey().equals(name)) {
                    try {
                        c.getValue().close();
                        connections.remove(c.getKey());
                        slf4jLogger
                                .info("=========== MySQL JDBC destroyed.....  ===========");
                    } catch (SQLException e) {
                       
                    }
                }
            }*/
        }
    }
}
