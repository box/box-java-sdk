package com.box.sdk;

import static java.util.logging.Level.FINE;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Level.WARNING;

import java.util.logging.Logger;

public final class BoxLogger {
    private static final BoxLogger DEFAULT_LOGGER = new BoxLogger("com.box.sdk");
    private final Logger logger;

    private BoxLogger(String name) {
        logger = Logger.getLogger(name);
    }

    public static BoxLogger defaultLogger() {
        return DEFAULT_LOGGER;
    }

    public void debug(String message) {
        logger.fine(message);
    }

    public void warn(String message) {
        logger.warning(message);
    }

    public void error(String message) {
        logger.severe(message);
    }

    public boolean isDebugEnabled() {
        return logger.isLoggable(FINE);
    }

    public void enableDebug() {
        this.logger.setLevel(FINE);
    }

    boolean isWarnEnabled() {
        return logger.isLoggable(WARNING);
    }

    boolean isErrorEnabled() {
        return logger.isLoggable(SEVERE);
    }
}
