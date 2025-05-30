package com.box.sdkgen.managers.metadatacascadepolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteMetadataCascadePolicyByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteMetadataCascadePolicyByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteMetadataCascadePolicyByIdHeaders(
      DeleteMetadataCascadePolicyByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteMetadataCascadePolicyByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteMetadataCascadePolicyByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteMetadataCascadePolicyByIdHeaders build() {
      return new DeleteMetadataCascadePolicyByIdHeaders(this);
    }
  }
}
