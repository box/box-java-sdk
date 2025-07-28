package com.box.sdkgen.managers.sharedlinksfiles;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class RemoveSharedLinkFromFileHeaders {

  public Map<String, String> extraHeaders;

  public RemoveSharedLinkFromFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected RemoveSharedLinkFromFileHeaders(Builder builder) {
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

    public RemoveSharedLinkFromFileHeaders build() {
      return new RemoveSharedLinkFromFileHeaders(this);
    }
  }
}
