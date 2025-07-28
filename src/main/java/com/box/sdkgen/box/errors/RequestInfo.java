package com.box.sdkgen.box.errors;

import com.box.sdkgen.internal.logging.DataSanitizer;
import java.util.Map;
import java.util.stream.Collectors;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;

public class RequestInfo {

  public final String method;

  public final String url;

  public final Map<String, String> queryParams;

  public final Map<String, String> headers;

  public String body;

  public RequestInfo(
      String method, String url, Map<String, String> queryParams, Map<String, String> headers) {
    this.method = method;
    this.url = url;
    this.queryParams = queryParams;
    this.headers = headers;
  }

  protected RequestInfo(Builder builder) {
    this.method = builder.method;
    this.url = builder.url;
    this.queryParams = builder.queryParams;
    this.headers = builder.headers;
    this.body = builder.body;
  }

  public String getMethod() {
    return method;
  }

  public String getUrl() {
    return url;
  }

  public Map<String, String> getQueryParams() {
    return queryParams;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public String getBody() {
    return body;
  }

  public static class Builder {

    protected final String method;

    protected final String url;

    protected final Map<String, String> queryParams;

    protected final Map<String, String> headers;

    protected String body;

    public Builder(
        String method, String url, Map<String, String> queryParams, Map<String, String> headers) {
      this.method = method;
      this.url = url;
      this.queryParams = queryParams;
      this.headers = headers;
    }

    public Builder body(String body) {
      this.body = body;
      return this;
    }

    public RequestInfo build() {
      return new RequestInfo(this);
    }
  }

  public static RequestInfo fromRequest(Request request) {
    Builder requestInfoBuilder =
        new Builder(
                request.method(),
                request.url().toString(),
                request.url().queryParameterNames().stream()
                    .collect(
                        Collectors.toMap(name -> name, name -> request.url().queryParameter(name))),
                request.headers().toMultimap().entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get(0))))
            .body(getRequestBodyAsString(request.body()));

    return new RequestInfo(requestInfoBuilder);
  }

  private static String getRequestBodyAsString(RequestBody requestBody) {
    try {
      if (requestBody != null) {
        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        return buffer.readUtf8();
      }
    } catch (Exception e) {
      return null;
    }
    return null;
  }

  String print(DataSanitizer dataSanitizer) {
    Map<String, String> sanitizedHeaders =
        dataSanitizer == null ? headers : dataSanitizer.sanitizeHeaders(headers);
    return "RequestInfo{"
        + "\n\tmethod='"
        + method
        + '\''
        + ", \n\turl='"
        + url
        + '\''
        + ", \n\tqueryParams="
        + queryParams
        + ", \n\theaders="
        + sanitizedHeaders
        + ", \n\tbody='"
        + body
        + '\''
        + '}';
  }
}
