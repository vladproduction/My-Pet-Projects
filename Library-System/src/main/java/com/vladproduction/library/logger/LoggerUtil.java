package com.vladproduction.library.logger;

import com.vladproduction.library.utils.PropertiesUtils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerUtil {
    private static Logger logger = Logger.getLogger("LibraryLogger");
    private static final PropertiesUtils propertiesUtil = new PropertiesUtils();

    /**
     * Static Initialization Block:
     * The static block initializes the logger when the LoggerUtil class is loaded.
     * This ensures that the logger and its handlers are properly configured before they are used anywhere else in the application.
     * */
    static {
        try {
            // Read logging settings from properties file
            String logFilePath = propertiesUtil.getProperty("log.filepath");
            Level logLevel = Level.parse(propertiesUtil.getProperty("log.level").toUpperCase());

            // Create a FileHandler to log to a file
            FileHandler fileHandler = new FileHandler(logFilePath, true); // true for append mode
            fileHandler.setFormatter(new SimpleFormatter()); // Use a simple text formatter

            // Add the file handler to the logger
            logger.addHandler(fileHandler);
            logger.setLevel(logLevel); // Set the logger level from properties
        } catch (IOException e) {
            logger.severe("Failed to initialize logger: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }

}
