package com.box.sdkgen.managers.storagepolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetStoragePolicyByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetStoragePolicyByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetStoragePolicyByIdHeaders(GetStoragePolicyByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetStoragePolicyByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetStoragePolicyByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetStoragePolicyByIdHeaders build() {
      return new GetStoragePolicyByIdHeaders(this);
    }
  }
}
