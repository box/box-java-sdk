package com.box.sdkgen.managers.metadatacascadepolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetMetadataCascadePolicyByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetMetadataCascadePolicyByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetMetadataCascadePolicyByIdHeaders(
      GetMetadataCascadePolicyByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetMetadataCascadePolicyByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetMetadataCascadePolicyByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetMetadataCascadePolicyByIdHeaders build() {
      return new GetMetadataCascadePolicyByIdHeaders(this);
    }
  }
}
