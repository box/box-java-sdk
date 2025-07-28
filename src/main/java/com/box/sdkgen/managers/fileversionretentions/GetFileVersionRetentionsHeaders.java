package com.box.sdkgen.managers.fileversionretentions;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileVersionRetentionsHeaders {

  public Map<String, String> extraHeaders;

  public GetFileVersionRetentionsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileVersionRetentionsHeaders(Builder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileVersionRetentionsHeaders build() {
      return new GetFileVersionRetentionsHeaders(this);
    }
  }
}
