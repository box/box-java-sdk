package com.box.sdkgen.schemas.realtimeserver;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** A real-time server that can be used for long polling user events. */
@JsonFilter("nullablePropertyFilter")
public class RealtimeServer extends SerializableObject {

  /** The value will always be `realtime_server`. */
  protected String type;

  /** The URL for the server. */
  protected String url;

  /** The time in minutes for which this server is available. */
  protected String ttl;

  /**
   * The maximum number of retries this server will allow before a new long poll should be started
   * by getting a [new list of server](#options-events).
   */
  @JsonProperty("max_retries")
  protected String maxRetries;

  /**
   * The maximum number of seconds without a response after which you should retry the long poll
   * connection.
   *
   * <p>This helps to overcome network issues where the long poll looks to be working but no
   * packages are coming through.
   */
  @JsonProperty("retry_timeout")
  protected Long retryTimeout;

  public RealtimeServer() {
    super();
  }

  protected RealtimeServer(Builder builder) {
    super();
    this.type = builder.type;
    this.url = builder.url;
    this.ttl = builder.ttl;
    this.maxRetries = builder.maxRetries;
    this.retryTimeout = builder.retryTimeout;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getType() {
    return type;
  }

  public String getUrl() {
    return url;
  }

  public String getTtl() {
    return ttl;
  }

  public String getMaxRetries() {
    return maxRetries;
  }

  public Long getRetryTimeout() {
    return retryTimeout;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RealtimeServer casted = (RealtimeServer) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(url, casted.url)
        && Objects.equals(ttl, casted.ttl)
        && Objects.equals(maxRetries, casted.maxRetries)
        && Objects.equals(retryTimeout, casted.retryTimeout);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, url, ttl, maxRetries, retryTimeout);
  }

  @Override
  public String toString() {
    return "RealtimeServer{"
        + "type='"
        + type
        + '\''
        + ", "
        + "url='"
        + url
        + '\''
        + ", "
        + "ttl='"
        + ttl
        + '\''
        + ", "
        + "maxRetries='"
        + maxRetries
        + '\''
        + ", "
        + "retryTimeout='"
        + retryTimeout
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String type;

    protected String url;

    protected String ttl;

    protected String maxRetries;

    protected Long retryTimeout;

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder url(String url) {
      this.url = url;
      return this;
    }

    public Builder ttl(String ttl) {
      this.ttl = ttl;
      return this;
    }

    public Builder maxRetries(String maxRetries) {
      this.maxRetries = maxRetries;
      return this;
    }

    public Builder retryTimeout(Long retryTimeout) {
      this.retryTimeout = retryTimeout;
      return this;
    }

    public RealtimeServer build() {
      return new RealtimeServer(this);
    }
  }
}
