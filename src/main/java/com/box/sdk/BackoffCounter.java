package com.box.sdk;

import java.util.logging.Level;
import java.util.logging.Logger;

class BackoffCounter {
    private static final Logger LOGGER = Logger.getLogger(BackoffCounter.class.getName());
    private static final int MIN_EXPONENT = 10;
    private static final int MAX_EXPONENT = 16;

    private final Time time;

    private int maxAttempts;
    private int attemptsRemaining;

    public BackoffCounter() {
        this.time = Time.getInstance();
    }

    public BackoffCounter(Time time) {
        this.time = time;
    }

    public int getAttemptsRemaining() {
        return this.attemptsRemaining;
    }

    public void waitBackoff() throws InterruptedException {
        int delay = this.calculateDelay();
        if (this.attemptsRemaining > 1) {
            LOGGER.log(Level.WARNING, String.format("Backing off for %d seconds before retrying %d more times.",
                (delay / 1000), this.attemptsRemaining));
        } else {
            LOGGER.log(Level.WARNING, String.format("Backing off for %d seconds before retrying %d more time.",
                (delay / 1000), this.attemptsRemaining));
        }

        this.time.waitDuration(delay);
    }

    public boolean decrement() {
        this.attemptsRemaining--;
        return (this.attemptsRemaining > 0);
    }

    public void reset(int maxAttempts) {
        this.maxAttempts = maxAttempts;
        this.attemptsRemaining = maxAttempts;
    }

    private int calculateDelay() {
        int exponent = (MIN_EXPONENT + (this.maxAttempts - (this.attemptsRemaining + 1)));
        if (exponent > MAX_EXPONENT) {
            exponent = MAX_EXPONENT;
        }

        return (2 << exponent);
    }
}
