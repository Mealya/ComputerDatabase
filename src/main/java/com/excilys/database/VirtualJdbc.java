package com.excilys.database;

import java.sql.Connection;

public interface VirtualJdbc {
    default Connection getConnection() {
        throw new UnsupportedOperationException("Not implemented");
    }
    
    default void closeConnect() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
