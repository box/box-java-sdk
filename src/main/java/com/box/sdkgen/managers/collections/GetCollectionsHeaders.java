package com.box.sdkgen.managers.collections;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetCollectionsHeaders {

  public Map<String, String> extraHeaders;

  public GetCollectionsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetCollectionsHeaders(GetCollectionsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetCollectionsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetCollectionsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetCollectionsHeaders build() {
      return new GetCollectionsHeaders(this);
    }
  }
}
