package com.box.sdkgen.managers.sharedlinksweblinks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetSharedLinkForWebLinkHeaders {

  public Map<String, String> extraHeaders;

  public GetSharedLinkForWebLinkHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetSharedLinkForWebLinkHeaders(Builder builder) {
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

    public GetSharedLinkForWebLinkHeaders build() {
      return new GetSharedLinkForWebLinkHeaders(this);
    }
  }
}
