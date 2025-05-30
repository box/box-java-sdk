package com.box.sdkgen.schemas.realtimeserver;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class RealtimeServer extends SerializableObject {

  protected String type;

  protected String url;

  protected String ttl;

  @JsonProperty("max_retries")
  protected String maxRetries;

  @JsonProperty("retry_timeout")
  protected Long retryTimeout;

  public RealtimeServer() {
    super();
  }

  protected RealtimeServer(RealtimeServerBuilder builder) {
    super();
    this.type = builder.type;
    this.url = builder.url;
    this.ttl = builder.ttl;
    this.maxRetries = builder.maxRetries;
    this.retryTimeout = builder.retryTimeout;
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

  public static class RealtimeServerBuilder {

    protected String type;

    protected String url;

    protected String ttl;

    protected String maxRetries;

    protected Long retryTimeout;

    public RealtimeServerBuilder type(String type) {
      this.type = type;
      return this;
    }

    public RealtimeServerBuilder url(String url) {
      this.url = url;
      return this;
    }

    public RealtimeServerBuilder ttl(String ttl) {
      this.ttl = ttl;
      return this;
    }

    public RealtimeServerBuilder maxRetries(String maxRetries) {
      this.maxRetries = maxRetries;
      return this;
    }

    public RealtimeServerBuilder retryTimeout(Long retryTimeout) {
      this.retryTimeout = retryTimeout;
      return this;
    }

    public RealtimeServer build() {
      return new RealtimeServer(this);
    }
  }
}
