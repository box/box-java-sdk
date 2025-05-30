package com.box.sdkgen.managers.groups;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetGroupsHeaders {

  public Map<String, String> extraHeaders;

  public GetGroupsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetGroupsHeaders(GetGroupsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetGroupsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetGroupsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetGroupsHeaders build() {
      return new GetGroupsHeaders(this);
    }
  }
}
