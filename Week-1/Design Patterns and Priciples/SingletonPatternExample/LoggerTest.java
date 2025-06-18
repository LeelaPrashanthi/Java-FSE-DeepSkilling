// src/LoggerTest.java

public class LoggerTest {
    public static void main(String[] args) {
        // Get Logger instances
        Logger logger_1 = Logger.getInstance();
        Logger logger_2 = Logger.getInstance();

        // Log messages
        logger_1.log("First log message.");
        logger_2.log("Second log message.");

        // Test if both instances are the same
        if (logger_1 == logger_2) {
            System.out.println("Both loggers are the same instance (Singleton works).");
        } else {
            System.out.println("Different instances (Singleton failed).");
        }
    }
}
