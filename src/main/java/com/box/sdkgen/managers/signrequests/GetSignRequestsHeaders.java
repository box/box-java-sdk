package com.box.sdkgen.managers.signrequests;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetSignRequestsHeaders {

  public Map<String, String> extraHeaders;

  public GetSignRequestsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetSignRequestsHeaders(GetSignRequestsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetSignRequestsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetSignRequestsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetSignRequestsHeaders build() {
      return new GetSignRequestsHeaders(this);
    }
  }
}
