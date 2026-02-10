package com.box.sdkgen.networking.fetchoptions;

import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class FetchOptions {

  /** URL of the request */
  public final String url;

  /** HTTP verb of the request */
  public final String method;

  /** HTTP query parameters */
  public Map<String, String> params;

  /** HTTP headers */
  public Map<String, String> headers;

  /** Request body of the request */
  public JsonNode data;

  /** Stream data of the request */
  public InputStream fileStream;

  /** Multipart data of the request */
  public List<MultipartItem> multipartData;

  /** Content type of the request body */
  public String contentType;

  /** Expected response format */
  public EnumWrapper<ResponseFormat> responseFormat;

  /** Authentication object */
  public Authentication auth;

  /** Network session object */
  public NetworkSession networkSession;

  /**
   * A boolean value indicate if the request should follow redirects. Defaults to True. Not
   * supported in Browser environment.
   */
  public Boolean followRedirects;

  public FetchOptions(String url, String method) {
    this.url = url;
    this.method = method;
    this.contentType = "application/json";
    this.responseFormat = new EnumWrapper<ResponseFormat>(ResponseFormat.JSON);
    this.followRedirects = true;
  }

  protected FetchOptions(Builder builder) {
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

  public static class Builder {

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

    public Builder(String url, String method) {
      this.url = url;
      this.method = method;
    }

    public Builder params(Map<String, String> params) {
      this.params = params;
      return this;
    }

    public Builder headers(Map<String, String> headers) {
      this.headers = headers;
      return this;
    }

    public Builder data(JsonNode data) {
      this.data = data;
      return this;
    }

    public Builder fileStream(InputStream fileStream) {
      this.fileStream = fileStream;
      return this;
    }

    public Builder multipartData(List<MultipartItem> multipartData) {
      this.multipartData = multipartData;
      return this;
    }

    public Builder contentType(String contentType) {
      this.contentType = contentType;
      return this;
    }

    public Builder responseFormat(ResponseFormat responseFormat) {
      this.responseFormat = new EnumWrapper<ResponseFormat>(responseFormat);
      return this;
    }

    public Builder responseFormat(EnumWrapper<ResponseFormat> responseFormat) {
      this.responseFormat = responseFormat;
      return this;
    }

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public Builder followRedirects(Boolean followRedirects) {
      this.followRedirects = followRedirects;
      return this;
    }

    public FetchOptions build() {
      if (this.contentType == null) {
        this.contentType = "application/json";
      }
      if (this.responseFormat == null) {
        this.responseFormat = new EnumWrapper<ResponseFormat>(ResponseFormat.JSON);
      }
      if (this.followRedirects == null) {
        this.followRedirects = true;
      }
      return new FetchOptions(this);
    }
  }
}
