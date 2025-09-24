package com.box.sdkgen.managers.metadatacascadepolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateMetadataCascadePolicyHeaders {

  public Map<String, String> extraHeaders;

  public CreateMetadataCascadePolicyHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateMetadataCascadePolicyHeaders(Builder builder) {
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

    public CreateMetadataCascadePolicyHeaders build() {
      return new CreateMetadataCascadePolicyHeaders(this);
    }
  }
}
