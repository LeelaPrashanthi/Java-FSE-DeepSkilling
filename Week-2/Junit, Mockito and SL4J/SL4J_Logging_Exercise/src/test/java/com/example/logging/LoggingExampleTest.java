package com.example.logging;

import org.junit.Test;

public class LoggingExampleTest {

    @Test
    public void testLogErrorAndWarning() {
        LoggingExample le = new LoggingExample();
        le.logErrorAndWarning();

        // You won't assert log output directly here unless using tools like LogCaptor or SLF4J Test.
        // This test just ensures the method runs without exceptions.
    }
}
