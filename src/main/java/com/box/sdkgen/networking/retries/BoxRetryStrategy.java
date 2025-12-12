package com.box.sdkgen.networking.retries;

import static com.box.sdkgen.internal.utils.UtilsManager.random;

import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;

public class BoxRetryStrategy implements RetryStrategy {

  public int maxAttempts;

  public double retryRandomizationFactor;

  public double retryBaseInterval;

  public int maxRetriesOnException;

  public BoxRetryStrategy() {
    this.maxAttempts = 5;
    this.retryRandomizationFactor = 0.5;
    this.retryBaseInterval = 1;
    this.maxRetriesOnException = 2;
  }

  protected BoxRetryStrategy(Builder builder) {
    this.maxAttempts = builder.maxAttempts;
    this.retryRandomizationFactor = builder.retryRandomizationFactor;
    this.retryBaseInterval = builder.retryBaseInterval;
    this.maxRetriesOnException = builder.maxRetriesOnException;
  }

  @Override
  public boolean shouldRetry(
      FetchOptions fetchOptions, FetchResponse fetchResponse, int attemptNumber) {
    if (fetchResponse.getStatus() == 0) {
      return attemptNumber <= this.maxRetriesOnException;
    }
    boolean isSuccessful = fetchResponse.getStatus() >= 200 && fetchResponse.getStatus() < 400;
    String retryAfterHeader =
        (fetchResponse.getHeaders().containsKey("Retry-After")
            ? fetchResponse.getHeaders().get("Retry-After")
            : null);
    boolean isAcceptedWithRetryAfter =
        fetchResponse.getStatus() == 202 && !(retryAfterHeader == null);
    if (attemptNumber >= this.maxAttempts) {
      return false;
    }
    if (isAcceptedWithRetryAfter) {
      return true;
    }
    if (fetchResponse.getStatus() >= 500) {
      return true;
    }
    if (fetchResponse.getStatus() == 429) {
      return true;
    }
    if (fetchResponse.getStatus() == 401 && !(fetchOptions.getAuth() == null)) {
      fetchOptions.getAuth().refreshToken(fetchOptions.getNetworkSession());
      return true;
    }
    if (isSuccessful) {
      return false;
    }
    return false;
  }

  @Override
  public double retryAfter(
      FetchOptions fetchOptions, FetchResponse fetchResponse, int attemptNumber) {
    String retryAfterHeader =
        (fetchResponse.getHeaders().containsKey("Retry-After")
            ? fetchResponse.getHeaders().get("Retry-After")
            : null);
    if (!(retryAfterHeader == null)) {
      return Double.parseDouble(retryAfterHeader);
    }
    double randomization =
        random(1 - this.retryRandomizationFactor, 1 + this.retryRandomizationFactor);
    double exponential = Math.pow(2, attemptNumber);
    return exponential * this.retryBaseInterval * randomization;
  }

  public int getMaxAttempts() {
    return maxAttempts;
  }

  public double getRetryRandomizationFactor() {
    return retryRandomizationFactor;
  }

  public double getRetryBaseInterval() {
    return retryBaseInterval;
  }

  public int getMaxRetriesOnException() {
    return maxRetriesOnException;
  }

  public static class Builder {

    protected int maxAttempts;

    protected double retryRandomizationFactor;

    protected double retryBaseInterval;

    protected int maxRetriesOnException;

    public Builder() {
      this.maxAttempts = 5;
      this.retryRandomizationFactor = 0.5;
      this.retryBaseInterval = 1;
      this.maxRetriesOnException = 2;
    }

    public Builder maxAttempts(int maxAttempts) {
      this.maxAttempts = maxAttempts;
      return this;
    }

    public Builder retryRandomizationFactor(double retryRandomizationFactor) {
      this.retryRandomizationFactor = retryRandomizationFactor;
      return this;
    }

    public Builder retryBaseInterval(double retryBaseInterval) {
      this.retryBaseInterval = retryBaseInterval;
      return this;
    }

    public Builder maxRetriesOnException(int maxRetriesOnException) {
      this.maxRetriesOnException = maxRetriesOnException;
      return this;
    }

    public BoxRetryStrategy build() {
      return new BoxRetryStrategy(this);
    }
  }
}
