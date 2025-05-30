package com.box.sdkgen.managers.groups;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetGroupByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetGroupByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetGroupByIdHeaders(GetGroupByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetGroupByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetGroupByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetGroupByIdHeaders build() {
      return new GetGroupByIdHeaders(this);
    }
  }
}
