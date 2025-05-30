package com.box.sdkgen.networking.retries;

import static com.box.sdkgen.internal.utils.UtilsManager.random;

import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;

public class BoxRetryStrategy implements RetryStrategy {

  public int maxAttempts;

  public double retryRandomizationFactor;

  public double retryBaseInterval;

  public BoxRetryStrategy() {
    this.maxAttempts = 5;
    this.retryRandomizationFactor = 0.5;
    this.retryBaseInterval = 1;
  }

  protected BoxRetryStrategy(BoxRetryStrategyBuilder builder) {
    this.maxAttempts = builder.maxAttempts;
    this.retryRandomizationFactor = builder.retryRandomizationFactor;
    this.retryBaseInterval = builder.retryBaseInterval;
  }

  @Override
  public boolean shouldRetry(
      FetchOptions fetchOptions, FetchResponse fetchResponse, int attemptNumber) {
    boolean isSuccessful = fetchResponse.getStatus() >= 200 && fetchResponse.getStatus() < 400;
    String retryAfterHeader = fetchResponse.getHeaders().get("Retry-After");
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
    String retryAfterHeader = fetchResponse.getHeaders().get("Retry-After");
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

  public static class BoxRetryStrategyBuilder {

    protected int maxAttempts;

    protected double retryRandomizationFactor;

    protected double retryBaseInterval;

    public BoxRetryStrategyBuilder maxAttempts(int maxAttempts) {
      this.maxAttempts = maxAttempts;
      return this;
    }

    public BoxRetryStrategyBuilder retryRandomizationFactor(double retryRandomizationFactor) {
      this.retryRandomizationFactor = retryRandomizationFactor;
      return this;
    }

    public BoxRetryStrategyBuilder retryBaseInterval(double retryBaseInterval) {
      this.retryBaseInterval = retryBaseInterval;
      return this;
    }

    public BoxRetryStrategy build() {
      return new BoxRetryStrategy(this);
    }
  }
}
