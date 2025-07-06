package com.cognizant.ormlearn.service.exception;

/**
 * Custom exception thrown when a country is not found by its code
 */
public class CountryNotFoundException extends Exception {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Default constructor
     */
    public CountryNotFoundException() {
        super();
    }
    
    /**
     * Constructor with message
     * @param message the error message
     */
    public CountryNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Constructor with message and cause
     * @param message the error message
     * @param cause the cause of the exception
     */
    public CountryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
} 