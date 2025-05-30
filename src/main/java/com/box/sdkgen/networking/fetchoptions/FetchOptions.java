package com.box.sdkgen.networking.fetchoptions;

import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class FetchOptions {

  public final String url;

  public final String method;

  public Map<String, String> params;

  public Map<String, String> headers;

  public JsonNode data;

  public InputStream fileStream;

  public List<MultipartItem> multipartData;

  public String contentType;

  public EnumWrapper<ResponseFormat> responseFormat;

  public Authentication auth;

  public NetworkSession networkSession;

  public Boolean followRedirects;

  public FetchOptions(String url, String method) {
    this.url = url;
    this.method = method;
    this.contentType = "application/json";
    this.responseFormat = new EnumWrapper<ResponseFormat>(ResponseFormat.JSON);
    this.followRedirects = true;
  }

  protected FetchOptions(FetchOptionsBuilder builder) {
    this.url = builder.url;
    this.method = builder.method;
    this.params = builder.params;
    this.headers = builder.headers;
    this.data = builder.data;
    this.fileStream = builder.fileStream;
    this.multipartData = builder.multipartData;
    this.contentType = builder.contentType;
    this.responseFormat = builder.responseFormat;
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
    this.followRedirects = builder.followRedirects;
  }

  public String getUrl() {
    return url;
  }

  public String getMethod() {
    return method;
  }

  public Map<String, String> getParams() {
    return params;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public JsonNode getData() {
    return data;
  }

  public InputStream getFileStream() {
    return fileStream;
  }

  public List<MultipartItem> getMultipartData() {
    return multipartData;
  }

  public String getContentType() {
    return contentType;
  }

  public EnumWrapper<ResponseFormat> getResponseFormat() {
    return responseFormat;
  }

  public Authentication getAuth() {
    return auth;
  }

  public NetworkSession getNetworkSession() {
    return networkSession;
  }

  public Boolean getFollowRedirects() {
    return followRedirects;
  }

  public static class FetchOptionsBuilder {

    protected final String url;

    protected final String method;

    protected Map<String, String> params;

    protected Map<String, String> headers;

    protected JsonNode data;

    protected InputStream fileStream;

    protected List<MultipartItem> multipartData;

    protected String contentType;

    protected EnumWrapper<ResponseFormat> responseFormat;

    protected Authentication auth;

    protected NetworkSession networkSession;

    protected Boolean followRedirects;

    public FetchOptionsBuilder(String url, String method) {
      this.url = url;
      this.method = method;
      this.contentType = "application/json";
      this.responseFormat = new EnumWrapper<ResponseFormat>(ResponseFormat.JSON);
      this.followRedirects = true;
    }

    public FetchOptionsBuilder params(Map<String, String> params) {
      this.params = params;
      return this;
    }

    public FetchOptionsBuilder headers(Map<String, String> headers) {
      this.headers = headers;
      return this;
    }

    public FetchOptionsBuilder data(JsonNode data) {
      this.data = data;
      return this;
    }

    public FetchOptionsBuilder fileStream(InputStream fileStream) {
      this.fileStream = fileStream;
      return this;
    }

    public FetchOptionsBuilder multipartData(List<MultipartItem> multipartData) {
      this.multipartData = multipartData;
      return this;
    }

    public FetchOptionsBuilder contentType(String contentType) {
      this.contentType = contentType;
      return this;
    }

    public FetchOptionsBuilder responseFormat(ResponseFormat responseFormat) {
      this.responseFormat = new EnumWrapper<ResponseFormat>(responseFormat);
      return this;
    }

    public FetchOptionsBuilder responseFormat(EnumWrapper<ResponseFormat> responseFormat) {
      this.responseFormat = responseFormat;
      return this;
    }

    public FetchOptionsBuilder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public FetchOptionsBuilder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public FetchOptionsBuilder followRedirects(Boolean followRedirects) {
      this.followRedirects = followRedirects;
      return this;
    }

    public FetchOptions build() {
      return new FetchOptions(this);
    }
  }
}
