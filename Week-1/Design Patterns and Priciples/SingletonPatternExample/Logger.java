// A class named Logger is created, that has a private static instance of itself.

public class Logger {
    // Create a private static instance of the class
    private static Logger instance;

    // Private constructor to prevent external instantiation
    //constructor of Logger is private.
    private Logger() {
        System.out.println("Logger instance created.");
    }

    //Public method to provide access to the single instance
    //A public static method to get the instance of the Logger class.
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Example method
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}
