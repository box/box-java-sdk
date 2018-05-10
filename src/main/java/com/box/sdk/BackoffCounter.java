package com.box.sdk;

import java.util.logging.Level;
import java.util.logging.Logger;

class BackoffCounter {
    private static final Logger LOGGER = Logger.getLogger(BackoffCounter.class.getName());
    private static final int BASE_TIMEOUT = 1000;
    private static final double RANDOM_FACTOR = 0.5;

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
        int exponent = this.maxAttempts - this.attemptsRemaining;
        double minWindow = 1 - RANDOM_FACTOR;
        double maxWindow = 1 + RANDOM_FACTOR;
        double jitter = (Math.random() * (maxWindow - minWindow)) + minWindow;

        return (int) (Math.pow(2, exponent) * BASE_TIMEOUT * jitter);
    }
}
