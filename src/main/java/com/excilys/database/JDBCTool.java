package com.excilys.database;

import java.sql.*;
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

    /**
     * Create an object Connection with a DB name
     * @param name The name of the database
     */
    public void linkToMySql() {
        slf4jLogger.info("=========== MySQL JDBC Connecting.....  ===========");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            slf4jLogger.error("Where is your MySQL JDBC Driver? : " + e.getMessage());
            return;
        }
        slf4jLogger.info("MySQL JDBC Driver Registered!");
    }
    
    public void connect(String nameDB) {
        if (nameDB == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        if (connections.get(nameDB) != null) {
            return;
        }
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+ nameDB +"?zeroDateTimeBehavior=convertToNull",
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
    }
    
    /**
     * Return the connection object linked to a name
     * @param name The name of the database
     * @return Connection object
     */
    public Connection getConnection(String name)  {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        return connections.get(name);
    }

    /**
     * Close the Connection linked to a name
     * @param name The name of the data base
     */
    public void closeConnect(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        for (Map.Entry<String, Connection> c : connections.entrySet()) {
            if (c.getKey().equals(name)) {
                try {
                    c.getValue().close();
                    connections.remove(c.getKey());
                    slf4jLogger.info("=========== MySQL JDBC destroyed.....  ===========");
                } catch (SQLException e) {
                    slf4jLogger.error("Deconnection failed! " + e.getMessage());
                }
            }
        }
    }
}

