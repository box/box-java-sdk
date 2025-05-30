package com.box.sdkgen.managers.filerequests;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileRequestByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetFileRequestByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileRequestByIdHeaders(GetFileRequestByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFileRequestByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFileRequestByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileRequestByIdHeaders build() {
      return new GetFileRequestByIdHeaders(this);
    }
  }
}
