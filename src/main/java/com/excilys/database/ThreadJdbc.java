package com.excilys.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.excilys.database.VirtualConnectTool;

public class ThreadJdbc implements VirtualConnectTool {
      
    private PoolJdbc pool = new PoolJdbc();
    // Thread local variable containing each thread's ID
    private final ThreadLocal<Connection> threadId = new ThreadLocal<>();
    
    private void reInitConnection() {
        threadId.set(pool.getConnection());
    }
    
    @Override
    public Connection getConnection() {
        if (threadId.get() == null) {
            reInitConnection();
        }
        return threadId.get();
    }

    @Override
    public void closeConnection(Connection c) {
        try {
            c.close();
            threadId.remove();
        } catch (SQLException e) {
            throw new ExceptionDB(e.getMessage());
        }
    }

    @Override
    public void rollBack(Connection c) {
        try {
            c.rollback();
        } catch (SQLException e) {
            throw new ExceptionDB(e.getMessage());
        }
    }

}
