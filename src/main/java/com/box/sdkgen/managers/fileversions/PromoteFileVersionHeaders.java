package com.box.sdkgen.managers.fileversions;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class PromoteFileVersionHeaders {

  public Map<String, String> extraHeaders;

  public PromoteFileVersionHeaders() {
    this.extraHeaders = mapOf();
  }

  protected PromoteFileVersionHeaders(Builder builder) {
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

    public PromoteFileVersionHeaders build() {
      return new PromoteFileVersionHeaders(this);
    }
  }
}
