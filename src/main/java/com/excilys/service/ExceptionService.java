package com.excilys.service;

public class ExceptionService extends RuntimeException {

    private static final long serialVersionUID = -6652645844917840624L;

    public ExceptionService(String message) {
        super(message);
    }

}
