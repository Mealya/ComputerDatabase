package com.excilys.database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Angot Maxime on 19/04/16.
 */
public class JDBCTool {


    private static Map<String, Connection> connections = new HashMap<String, Connection>();

    public void connectToMySql(String name) throws SQLException {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        if (connections.get(name) != null) {
            return;
        }
        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //TODO gérer les throws
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/"+ name +"?zeroDateTimeBehavior=convertToNull",
                            "admincdb", "qwerty1234");

        } catch (SQLException e) {
            //TODO g�rer les throws
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
            //connectTODB = connection;
            connections.put(name, connection);
        } else {
            //TODO gérer les throws
            System.out.println("Failed to make connection!");
            return;
        }

    }

    public Connection getConnection(String name) throws SQLException {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        System.out.println(name);
        return connections.get(name);
    }

    public void closeConnect(String name) throws SQLException {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        for (Map.Entry<String, Connection> c : connections.entrySet()) {
            if (c.getKey().equals(name)) {
                c.getValue().close();
                connections.remove(c);
            }
        }
    }
}

