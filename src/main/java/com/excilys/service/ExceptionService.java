package com.excilys.service;

public class ExceptionService extends RuntimeException {

    private static final long serialVersionUID = -6652645844917840624L;
    
    /**
     * Create a custom exception for the service package.
     * @param message The message error to display
     */
    public ExceptionService(String message) {
        super(message);
    }

}
