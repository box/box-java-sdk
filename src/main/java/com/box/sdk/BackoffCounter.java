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

  /**
   * Waits for some random amount of time {@link BackoffCounter#calculateDelay()}
   *
   * @throws InterruptedException
   */
  public void waitBackoff() throws InterruptedException {
    int delay = this.calculateDelay();
    this.waitBackoff(delay);
  }

  /**
   * Waits for specified amount of miliseconds.
   *
   * @param delay Time to wait for in miliseconds.
   * @throws InterruptedException
   */
  public void waitBackoff(int delay) throws InterruptedException {
    if (LOGGER.isWarnEnabled()) {
      LOGGER.warn(
          String.format(
              "Backing off for %d seconds before retrying %d more time%s.",
              (delay / 1000), this.attemptsRemaining, this.attemptsRemaining > 1 ? "s" : ""));
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

  /**
   * Generates some random amount of time to backoff. Time is within <16000, 48000) ms
   *
   * @return Time in miliseconds.
   */
  private int calculateDelay() {
    int exponent = this.maxAttempts - this.attemptsRemaining;
    double minWindow = 1 - RANDOM_FACTOR;
    double maxWindow = 1 + RANDOM_FACTOR;
    double jitter = (Math.random() * (maxWindow - minWindow)) + minWindow;
    return (int) (Math.pow(2, exponent) * BASE_TIMEOUT * jitter);
  }
}
