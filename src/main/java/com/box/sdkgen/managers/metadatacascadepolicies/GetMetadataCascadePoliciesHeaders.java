package com.box.sdkgen.managers.metadatacascadepolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetMetadataCascadePoliciesHeaders {

  public Map<String, String> extraHeaders;

  public GetMetadataCascadePoliciesHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetMetadataCascadePoliciesHeaders(GetMetadataCascadePoliciesHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetMetadataCascadePoliciesHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetMetadataCascadePoliciesHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetMetadataCascadePoliciesHeaders build() {
      return new GetMetadataCascadePoliciesHeaders(this);
    }
  }
}
