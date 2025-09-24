package com.box.sdkgen.box.errors;

import com.box.sdkgen.internal.logging.DataSanitizer;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;

public class ResponseInfo {

  public final int statusCode;

  public final Map<String, String> headers;

  public JsonNode body;

  public String rawBody;

  public String code;

  public JsonNode contextInfo;

  public String requestId;

  public String helpUrl;

  public ResponseInfo(int statusCode, Map<String, String> headers) {
    this.statusCode = statusCode;
    this.headers = headers;
  }

  protected ResponseInfo(Builder builder) {
    this.statusCode = builder.statusCode;
    this.headers = builder.headers;
    this.body = builder.body;
    this.rawBody = builder.rawBody;
    this.code = builder.code;
    this.contextInfo = builder.contextInfo;
    this.requestId = builder.requestId;
    this.helpUrl = builder.helpUrl;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public JsonNode getBody() {
    return body;
  }

  public String getRawBody() {
    return rawBody;
  }

  public String getCode() {
    return code;
  }

  public JsonNode getContextInfo() {
    return contextInfo;
  }

  public String getRequestId() {
    return requestId;
  }

  public String getHelpUrl() {
    return helpUrl;
  }

  public static class Builder {

    protected final int statusCode;

    protected final Map<String, String> headers;

    protected JsonNode body;

    protected String rawBody;

    protected String code;

    protected JsonNode contextInfo;

    protected String requestId;

    protected String helpUrl;

    public Builder(int statusCode, Map<String, String> headers) {
      this.statusCode = statusCode;
      this.headers = headers;
    }

    public Builder body(JsonNode body) {
      this.body = body;
      return this;
    }

    public Builder rawBody(String rawBody) {
      this.rawBody = rawBody;
      return this;
    }

    public Builder code(String code) {
      this.code = code;
      return this;
    }

    public Builder contextInfo(JsonNode contextInfo) {
      this.contextInfo = contextInfo;
      return this;
    }

    public Builder requestId(String requestId) {
      this.requestId = requestId;
      return this;
    }

    public Builder helpUrl(String helpUrl) {
      this.helpUrl = helpUrl;
      return this;
    }

    public ResponseInfo build() {
      return new ResponseInfo(this);
    }
  }

  public static ResponseInfo fromResponse(FetchResponse fetchResponse, String rawResponseBody) {
    Builder builder = new Builder(fetchResponse.getStatus(), fetchResponse.getHeaders());
    JsonNode body = fetchResponse.getData();
    if (body == null) {
      builder.rawBody(rawResponseBody);
    } else {
      builder
          .body(body)
          .rawBody(body.toString())
          .code(body.get("code") != null ? body.get("code").asText("") : null)
          .contextInfo(body.get("context_info") != null ? body.get("context_info") : null)
          .requestId(body.get("request_id") != null ? body.get("request_id").asText() : null)
          .helpUrl(body.get("help_url") != null ? body.get("help_url").asText() : null);
    }

    return builder.build();
  }

  String print(DataSanitizer dataSanitizer) {
    Map<String, String> sanitizedHeaders =
        dataSanitizer == null ? headers : dataSanitizer.sanitizeHeaders(headers);
    JsonNode sanitizedBody = dataSanitizer == null ? body : dataSanitizer.sanitizeBody(body);
    return "ResponseInfo{"
        + "\n\tstatusCode="
        + statusCode
        + ", \n\theaders="
        + sanitizedHeaders
        + ", \n\tbody="
        + sanitizedBody
        + ", \n\tcode='"
        + code
        + '\''
        + ", \n\tcontextInfo="
        + contextInfo
        + ", \n\trequestId='"
        + requestId
        + '\''
        + ", \n\thelpUrl='"
        + helpUrl
        + '\''
        + '}';
  }
}
