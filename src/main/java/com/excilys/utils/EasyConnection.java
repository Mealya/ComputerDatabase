package com.excilys.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class EasyConnection {

    public static void closeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void rollBackConnection(Connection c) {
        try {
            c.rollback();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
