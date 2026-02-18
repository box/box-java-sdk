package com.box.sdkgen.networking.timeoutconfig;

public class TimeoutConfig {

  public Long connectionTimeoutMs;

  public Long readTimeoutMs;

  public TimeoutConfig() {}

  protected TimeoutConfig(Builder builder) {
    this.connectionTimeoutMs = builder.connectionTimeoutMs;
    this.readTimeoutMs = builder.readTimeoutMs;
  }

  public Long getConnectionTimeoutMs() {
    return connectionTimeoutMs;
  }

  public Long getReadTimeoutMs() {
    return readTimeoutMs;
  }

  public static class Builder {

    protected Long connectionTimeoutMs;

    protected Long readTimeoutMs;

    public Builder connectionTimeoutMs(Long connectionTimeoutMs) {
      this.connectionTimeoutMs = connectionTimeoutMs;
      return this;
    }

    public Builder readTimeoutMs(Long readTimeoutMs) {
      this.readTimeoutMs = readTimeoutMs;
      return this;
    }

    public TimeoutConfig build() {
      return new TimeoutConfig(this);
    }
  }
}
