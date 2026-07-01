package com.box.sdkgen.networking.timeoutconfig;

public class TimeoutConfig {

  public Long connectionTimeoutMs;

  public Long readTimeoutMs;

  public Long requestTimeoutMs;

  public TimeoutConfig() {}

  protected TimeoutConfig(Builder builder) {
    this.connectionTimeoutMs = builder.connectionTimeoutMs;
    this.readTimeoutMs = builder.readTimeoutMs;
    this.requestTimeoutMs = builder.requestTimeoutMs;
  }

  public Long getConnectionTimeoutMs() {
    return connectionTimeoutMs;
  }

  public Long getReadTimeoutMs() {
    return readTimeoutMs;
  }

  public Long getRequestTimeoutMs() {
    return requestTimeoutMs;
  }

  public static class Builder {

    protected Long connectionTimeoutMs;

    protected Long readTimeoutMs;

    protected Long requestTimeoutMs;

    public Builder connectionTimeoutMs(Long connectionTimeoutMs) {
      this.connectionTimeoutMs = connectionTimeoutMs;
      return this;
    }

    public Builder readTimeoutMs(Long readTimeoutMs) {
      this.readTimeoutMs = readTimeoutMs;
      return this;
    }

    public Builder requestTimeoutMs(Long requestTimeoutMs) {
      this.requestTimeoutMs = requestTimeoutMs;
      return this;
    }

    public TimeoutConfig build() {
      return new TimeoutConfig(this);
    }
  }
}
