package 4_BehavioralDPs;

// Chain of Responsibility Design Pattern is a behavioral design pattern that allows an object to pass a request along a chain of potential handlers until the request is handled. 
// This pattern decouples the sender of the request from its receivers, allowing multiple objects to handle the request without the sender needing to know which object will handle it.

// In the Chain of Responsibility design pattern, we have the following components:
// 1. Handler: This is an interface or abstract class that defines a method for handling requests and a reference to the next handler in the chain.
// 2. Concrete Handlers: These are classes that implement the Handler interface and provide specific implementations for handling requests. 
//    Each concrete handler can either handle the request or pass it to the next handler in the chain.    
// 3. Client: This is the object that initiates the request and sends it to the first handler in the chain.

// Example of Chain of Responsibility design pattern is a logging system where multiple loggers (handlers) can process log messages based on their severity levels.

// handler interface
interface Logger {
    void setNext(Logger nextLogger); // sets the next responsible handler in the chain
    void logMessage(int level, String message); // handler method to process the request
}

// concrete handler 1: Console Logger
class ConsoleLogger implements Logger {
    private Logger nextLogger;

    @Override
    public void setNext(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    @Override
    public void logMessage(int level, String message) {
        if (level <= 1) {
            System.out.println("Console Logger: " + message);
        } else if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }
}

// concrete handler 2: File Logger
class FileLogger implements Logger {
    private Logger nextLogger;

    @Override
    public void setNext(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    @Override
    public void logMessage(int level, String message) {
        if (level <= 2) {
            System.out.println("File Logger: " + message);
        } else if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }
}

// concrete handler 3: Error Logger
class ErrorLogger implements Logger {
    private Logger nextLogger;

    @Override
    public void setNext(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    @Override
    public void logMessage(int level, String message) {
        if (level <= 3) {
            System.out.println("Error Logger: " + message);
        } else if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }
}

// Logger Factory class to create loggers based on the level
class LoggerFactory {
    public static Logger getLogger(int level) {
        Logger consoleLogger = new ConsoleLogger();
        Logger fileLogger = new FileLogger();
        Logger errorLogger = new ErrorLogger();

        consoleLogger.setNext(fileLogger);
        fileLogger.setNext(errorLogger);

        return consoleLogger;
    }
}

// client class
public class ChainOfResponsibility {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(1);

        logger.logMessage(1, "This is an information message.");
        logger.logMessage(2, "This is a warning message.");
        logger.logMessage(3, "This is an error message.");
    }

}

// In the above code, we have defined a `Logger` interface (Handler) that has two methods: `setNext()` and `logMessage()`.
// We have three concrete implementations of the `Logger` interface: `ConsoleLogger`, `FileLogger`, and `ErrorLogger` (Concrete Handlers).
// We also have a `LoggerFactory` class that creates a chain of loggers based on the severity level.
// Each concrete logger can handle log messages based on their severity levels and pass the request to the next logger in the chain if it cannot handle it.
