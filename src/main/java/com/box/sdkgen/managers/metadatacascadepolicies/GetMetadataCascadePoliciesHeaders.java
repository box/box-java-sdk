package com.box.sdkgen.managers.metadatacascadepolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetMetadataCascadePoliciesHeaders {

  public Map<String, String> extraHeaders;

  public GetMetadataCascadePoliciesHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetMetadataCascadePoliciesHeaders(Builder builder) {
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

    public GetMetadataCascadePoliciesHeaders build() {
      return new GetMetadataCascadePoliciesHeaders(this);
    }
  }
}
