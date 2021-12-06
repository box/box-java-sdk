package com.box.sdk;

import static java.util.logging.Level.FINE;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Level.WARNING;

import java.util.logging.Logger;

final class BoxLogger {
    private final Logger logger;

    private BoxLogger(String name) {
        logger = Logger.getLogger(name);
    }

    public static BoxLogger defaultLogger() {
        return new BoxLogger("com.box.sdk");
    }

    void debug(String message) {
        logger.fine(message);
    }

    void warn(String message) {
        logger.warning(message);
    }

    void error(String message) {
        logger.severe(message);
    }

    boolean isDebugEnabled() {
        return logger.isLoggable(FINE);
    }

    boolean isWarnEnabled() {
        return logger.isLoggable(WARNING);
    }

    boolean isErrorEnabled() {
        return logger.isLoggable(SEVERE);
    }
}
