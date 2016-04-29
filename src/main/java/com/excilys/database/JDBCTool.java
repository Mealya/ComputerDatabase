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
public class JDBCTool {

    private final Logger slf4jLogger = LoggerFactory.getLogger(JDBCTool.class);

    private static Map<String, Connection> connections = new HashMap<String, Connection>();
    private static Class<?> DB_driver = null;
    
    private final static Object LOCK_CREATE = new Object();
    private final static Object LOCK_DELETE = new Object();
    private final static Object LOCK_DRIVER = new Object();
    
    /**
     * Create an object Connection with a DB name.
     */
    private void linkToMySql() {
        slf4jLogger.info("=========== MySQL JDBC Connecting.....  ===========");

        try {
            DB_driver = Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            slf4jLogger.error("Where is your MySQL JDBC Driver? : "
                    + e.getMessage());
            return;
        }
        slf4jLogger.info("MySQL JDBC Driver Registered!");
    }

    public void connect(String nameDB) {
        if (nameDB == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        synchronized(LOCK_CREATE) {
            if (connections.get(nameDB) != null) {
                return;
            }
        }
        if (DB_driver == null) {
            synchronized (LOCK_DRIVER) {
                if (DB_driver == null) {
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
            slf4jLogger.error("Connection Failed! " + e.getMessage());

            return;
        }

        if (connection != null) {
            connections.put(nameDB, connection);
        } else {
            slf4jLogger.error("Failed to make connection!");
            return;
        }
        slf4jLogger.info("=========== MySQL JDBC created.....  ===========");
    }

    /**
     * Return the connection object linked to a name
     * 
     * @param name
     *            The name of the database
     * @return Connection object
     */
    public Connection getConnection(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        synchronized (LOCK_DELETE) {
            Connection c = connections.get(name);
            try {
                if (c.isClosed()) {
                    connect(name);
                }
            } catch (SQLException e) {
                slf4jLogger.error("Failed to make connection! " + e.getMessage());
            }
            return connections.get(name);
        }
         
    }

    /**
     * Close the Connection linked to a name
     * 
     * @param name
     *            The name of the data base
     */
    public void closeConnect(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        synchronized (LOCK_DELETE) {
            for (Map.Entry<String, Connection> c : connections.entrySet()) {
                if (c.getKey().equals(name)) {
                    try {
                        c.getValue().close();
                        connections.remove(c.getKey());
                        slf4jLogger
                                .info("=========== MySQL JDBC destroyed.....  ===========");
                    } catch (SQLException e) {
                        slf4jLogger.error("Deconnection failed! "
                                + e.getMessage());
                    }
                }
            }
        }
    }
}
