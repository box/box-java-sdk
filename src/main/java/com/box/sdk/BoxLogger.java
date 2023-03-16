package com.box.sdk;

import static java.util.logging.Level.ALL;
import static java.util.logging.Level.FINE;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.OFF;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Level.WARNING;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logger class.
 */
public final class BoxLogger {
    private static final BoxLogger DEFAULT_LOGGER = new BoxLogger("com.box.sdk");
    private final Logger logger;

    private BoxLogger(String name) {
        logger = Logger.getLogger(name);
    }

    /**
     * Get logger instance
     * @return logger instance.
     */
    public static BoxLogger defaultLogger() {
        return DEFAULT_LOGGER;
    }

    /**
     * Log message with level DEBUG {@link Level#FINE}
     * @param message message to log
     */
    public void debug(String message) {
        logger.fine(message);
    }

    /**
     * Log message with level INFO {@link Level#INFO}
     * @param message message to log
     */
    public void info(String message) {
        logger.info(message);
    }

    /**
     * Log message with level WARNING {@link Level#WARNING}
     * @param message message to log
     */
    public void warn(String message) {
        logger.warning(message);
    }

    /**
     * Log message with level WARNING {@link Level#SEVERE}
     * @param message message to log
     */
    public void error(String message) {
        logger.severe(message);
    }

    /**
     * Check if DEBUG level is enabled
     * @return true if DEBUG level is enabled
     */
    public boolean isDebugEnabled() {
        return logger.isLoggable(FINE);
    }

    /**
     * Check if INFO level is enabled
     * @return true if INFO level is enabled
     */
    public boolean isInfoEnabled() {
        return logger.isLoggable(INFO);
    }

    /**
     * Check if WARN level is enabled
     * @return true if WARN level is enabled
     */
    public boolean isWarnEnabled() {
        return logger.isLoggable(WARNING);
    }

    /**
     * Check if ERROR level is enabled
     * @return true if ERROR level is enabled
     */
    public boolean isErrorEnabled() {
        return logger.isLoggable(SEVERE);
    }

    /**
     *  Set logging level to ALL {@link Level#ALL}
     */
    public void setLevelToAll() {
        this.logger.setLevel(ALL);
    }

    /**
     *  Reset logging level to default.
     */
    public void resetToDefaultLevel() {
        this.logger.setLevel(null);
    }

    /**
     * Set logging level to INFO {@link Level#INFO}
     */
    public void setLevelToInfo() {
        this.logger.setLevel(INFO);
    }

    /**
     * Set logging level to WARNING {@link Level#WARNING}
     */
    public void setLevelToWarning() {
        this.logger.setLevel(WARNING);
    }

    /**
     * Set logging level to ERROR {@link Level#SEVERE}
     */
    public void setLevelToError() {
        this.logger.setLevel(SEVERE);
    }

    /**
     * Turns logging off
     */
    public void turnLoggingOff() {
        this.logger.setLevel(OFF);
    }

    /**
     * Specify whether this logger should send its output to its parent Logger. This means that any LogRecords
     * will also be written to the parent's Handlers, and potentially to its parent, recursively up the namespace.
     *
     * @param useParentHandlers true if output is to be sent to the logger's parent.
     * @throws SecurityException if a security manager exists, this logger is not anonymous,
     * and the caller does not have LoggingPermission("control").
     */
    public void setUseParentHandlers(boolean useParentHandlers) {
        this.logger.setUseParentHandlers(useParentHandlers);
    }

    /**
     * Adds handler to logger.
     * Check {@link Logger#addHandler(Handler)}
     * @param handler a logging handler to add.
     */
    public void addHandler(Handler handler) {
        logger.addHandler(handler);
    }

    /**
     * Removes handler from logger.
     * Will not fail if handler is null or not registered.
     * Check {@link Logger#removeHandler(Handler)}
     * @param handler a logging handler to remove.
     */
    public void removeHandler(Handler handler) {
        logger.removeHandler(handler);
    }
}
