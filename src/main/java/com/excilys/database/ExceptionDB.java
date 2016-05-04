package com.excilys.database;

public class ExceptionDB extends RuntimeException {

    private static final long serialVersionUID = 3568881160231071616L;

    /**
     * Create a custom exception for the Database
     * @param message The message of the error
     */
    public ExceptionDB(String message) {
        super(message);
    }

}
