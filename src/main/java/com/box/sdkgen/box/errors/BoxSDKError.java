package com.box.sdkgen.box.errors;

public class BoxSDKError extends RuntimeException {

  public final String message;

  public String timestamp;

  public Exception error;

  public String name;

  public BoxSDKError(String message) {
    this.message = message;
    this.name = "BoxSDKError";
  }

  public BoxSDKError(String message, Exception error) {
    this.message = message;
    this.error = error;
    this.name = "BoxSDKError";
  }

  protected BoxSDKError(Builder builder) {
    this.message = builder.message;
    this.timestamp = builder.timestamp;
    this.error = builder.error;
    this.name = builder.name;
  }

  public String getMessage() {
    return message;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public Object getError() {
    return error;
  }

  public String getName() {
    return name;
  }

  public static class Builder {

    protected final String message;

    protected String timestamp;

    protected Exception error;

    protected String name;

    public Builder(String message) {
      this.message = message;
      this.name = "BoxSDKError";
    }

    public Builder timestamp(String timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public Builder error(Exception error) {
      this.error = error;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public BoxSDKError build() {
      return new BoxSDKError(this);
    }
  }
}
