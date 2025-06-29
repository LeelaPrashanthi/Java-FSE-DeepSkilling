package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLoggerExample {
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggerExample.class);

    public void logUserAction(String username, String action) {
        logger.info("User {} performed the action: {}", username, action);
    }

    public static void main(String[] args) {
        ParameterizedLoggerExample example = new ParameterizedLoggerExample();
        example.logUserAction("leela", "login");
    }
}
