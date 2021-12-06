package com.box.sdk;

class BackoffCounter {
    private static final BoxLogger LOGGER = BoxLogger.defaultLogger();
    private static final int BASE_TIMEOUT = 1000;
    private static final double RANDOM_FACTOR = 0.5;

    private final Time time;

    private int maxAttempts;
    private int attemptsRemaining;

    BackoffCounter() {
        this(Time.getInstance());
    }

    BackoffCounter(Time time) {
        this.time = time;
    }

    public int getAttemptsRemaining() {
        return this.attemptsRemaining;
    }

    public void waitBackoff() throws InterruptedException {
        int delay = this.calculateDelay();
        this.waitBackoff(delay);
    }

    public void waitBackoff(int delay) throws InterruptedException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format(
                "Backing off for %d seconds before retrying %d more time%s.",
                (delay / 1000),
                this.attemptsRemaining,
                this.attemptsRemaining > 1 ? "s" : ""
            ));

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
