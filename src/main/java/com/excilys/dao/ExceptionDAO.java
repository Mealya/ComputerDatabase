package com.excilys.dao;

public class ExceptionDAO extends RuntimeException {

    private static final long serialVersionUID = -6652645844917840624L;

    public ExceptionDAO(String message) {
        super(message);
    }

}
