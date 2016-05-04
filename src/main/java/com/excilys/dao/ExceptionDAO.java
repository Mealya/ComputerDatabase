package com.excilys.dao;

public class ExceptionDAO extends RuntimeException {

    private static final long serialVersionUID = -6652645844917840624L;

    /**
     * Create a custom exception for the DAO.
     * @param message The exception message
     */
    public ExceptionDAO(String message) {
        super(message);
    }

}
