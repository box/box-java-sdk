package com.box.sdkgen.networking.fetchresponse;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.InputStream;
import java.util.Map;

public class FetchResponse {

  public String url;

  public final int status;

  public JsonNode data;

  public InputStream content;

  public final Map<String, String> headers;

  public FetchResponse(int status, Map<String, String> headers) {
    this.status = status;
    this.headers = headers;
  }

  protected FetchResponse(Builder builder) {
    this.url = builder.url;
    this.status = builder.status;
    this.data = builder.data;
    this.content = builder.content;
    this.headers = builder.headers;
  }

  public String getUrl() {
    return url;
  }

  public int getStatus() {
    return status;
  }

  public JsonNode getData() {
    return data;
  }

  public InputStream getContent() {
    return content;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public static class Builder {

    protected String url;

    protected final int status;

    protected JsonNode data;

    protected InputStream content;

    protected final Map<String, String> headers;

    public Builder(int status, Map<String, String> headers) {
      this.status = status;
      this.headers = headers;
    }

    public Builder url(String url) {
      this.url = url;
      return this;
    }

    public Builder data(JsonNode data) {
      this.data = data;
      return this;
    }

    public Builder content(InputStream content) {
      this.content = content;
      return this;
    }

    public FetchResponse build() {
      return new FetchResponse(this);
    }
  }
}
