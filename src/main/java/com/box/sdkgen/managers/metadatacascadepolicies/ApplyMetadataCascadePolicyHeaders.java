package com.box.sdkgen.managers.metadatacascadepolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class ApplyMetadataCascadePolicyHeaders {

  public Map<String, String> extraHeaders;

  public ApplyMetadataCascadePolicyHeaders() {
    this.extraHeaders = mapOf();
  }

  protected ApplyMetadataCascadePolicyHeaders(ApplyMetadataCascadePolicyHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class ApplyMetadataCascadePolicyHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public ApplyMetadataCascadePolicyHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public ApplyMetadataCascadePolicyHeaders build() {
      return new ApplyMetadataCascadePolicyHeaders(this);
    }
  }
}
