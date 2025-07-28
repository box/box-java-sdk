package com.box.sdkgen.box.errors;

import com.box.sdkgen.internal.logging.DataSanitizer;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.fasterxml.jackson.databind.JsonNode;
import java.time.LocalDateTime;
import java.util.Optional;
import okhttp3.Request;

public class BoxAPIError extends BoxSDKError {

  public final RequestInfo requestInfo;

  public final ResponseInfo responseInfo;

  private final DataSanitizer dataSanitizer;

  public BoxAPIError(String message, RequestInfo requestInfo, ResponseInfo responseInfo) {
    super(message);
    this.requestInfo = requestInfo;
    this.responseInfo = responseInfo;
    this.dataSanitizer = new DataSanitizer();
  }

  public static BoxAPIError fromAPICall(
      Request request,
      FetchResponse fetchResponse,
      String rawResponseBody,
      DataSanitizer dataSanitizer) {
    RequestInfo requestInfo = RequestInfo.fromRequest(request);
    ResponseInfo responseInfo = ResponseInfo.fromResponse(fetchResponse, rawResponseBody);

    String requestId =
        Optional.ofNullable(responseInfo.getBody())
            .map(body -> body.get("request_id"))
            .map(JsonNode::asText)
            .orElse("");

    return new Builder(
            String.format("Status %d; Request ID: %s", responseInfo.getStatusCode(), requestId),
            requestInfo,
            responseInfo)
        .timestamp(LocalDateTime.now().toString())
        .dataSanitizer(dataSanitizer)
        .build();
  }

  protected BoxAPIError(Builder builder) {
    super(builder);
    this.requestInfo = builder.requestInfo;
    this.responseInfo = builder.responseInfo;
    this.dataSanitizer = builder.dataSanitizer;
  }

  public RequestInfo getRequestInfo() {
    return requestInfo;
  }

  public ResponseInfo getResponseInfo() {
    return responseInfo;
  }

  public static class Builder extends BoxSDKError.Builder {

    protected final RequestInfo requestInfo;

    protected final ResponseInfo responseInfo;

    protected DataSanitizer dataSanitizer;

    public Builder(String message, RequestInfo requestInfo, ResponseInfo responseInfo) {
      super(message);
      this.requestInfo = requestInfo;
      this.responseInfo = responseInfo;
      this.dataSanitizer = new DataSanitizer();
    }

    @Override
    public Builder timestamp(String timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    @Override
    public Builder error(Exception error) {
      this.error = error;
      return this;
    }

    @Override
    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder dataSanitizer(DataSanitizer dataSanitizer) {
      this.dataSanitizer = dataSanitizer;
      return this;
    }

    public BoxAPIError build() {
      return new BoxAPIError(this);
    }
  }

  @Override
  public String toString() {
    return String.join(
        "",
        super.toString(),
        String.format("\nRequest: %s", requestInfo.print(dataSanitizer)),
        String.format("\nResponse: %s", responseInfo.print(dataSanitizer)));
  }
}
