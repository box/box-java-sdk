package com.box.sdkgen.managers.fileversionretentions;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileVersionRetentionsHeaders {

  public Map<String, String> extraHeaders;

  public GetFileVersionRetentionsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileVersionRetentionsHeaders(GetFileVersionRetentionsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFileVersionRetentionsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFileVersionRetentionsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileVersionRetentionsHeaders build() {
      return new GetFileVersionRetentionsHeaders(this);
    }
  }
}
