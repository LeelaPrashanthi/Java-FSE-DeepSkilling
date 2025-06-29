package com.example.logging;

import org.junit.Test;

public class ParameterizedLoggerExampleTest {

    @Test
    public void testLogUserAction() {
        ParameterizedLoggerExample example = new ParameterizedLoggerExample();
        example.logUserAction("testUser", "logout");
        // Just checks that logging works without errors
    }
}
