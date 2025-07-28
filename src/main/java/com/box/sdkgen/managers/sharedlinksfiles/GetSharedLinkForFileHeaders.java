package com.box.sdkgen.managers.sharedlinksfiles;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetSharedLinkForFileHeaders {

  public Map<String, String> extraHeaders;

  public GetSharedLinkForFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetSharedLinkForFileHeaders(Builder builder) {
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

    public GetSharedLinkForFileHeaders build() {
      return new GetSharedLinkForFileHeaders(this);
    }
  }
}
