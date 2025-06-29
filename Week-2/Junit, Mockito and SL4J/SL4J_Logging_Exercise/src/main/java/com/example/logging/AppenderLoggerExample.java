package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppenderLoggerExample {
    private static final Logger logger = LoggerFactory.getLogger(AppenderLoggerExample.class);

    public static void main(String[] args) {
        logger.debug("Debug message: Application started");
        logger.info("Info message: This will be logged to console and file");
        logger.error("Error message: Something went wrong");
    }
}
