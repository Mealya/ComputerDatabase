package com.excilys.database;

import java.sql.Connection;

public interface VirtualJdbc {
    /**
     * To get a connection for a DAO.
     * @return The connection
     */
    default Connection getConnection() {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    /**
     * To close the connection.
     * @param c The connection who needs to be closed
     */
    default void closeConnection(Connection c) {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    default void rollBack(Connection c) {
        //throw new UnsupportedOperationException("Not implemented");
    }
    
} 
