package com.box.sdkgen.managers.storagepolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetStoragePoliciesHeaders {

  public Map<String, String> extraHeaders;

  public GetStoragePoliciesHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetStoragePoliciesHeaders(GetStoragePoliciesHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetStoragePoliciesHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetStoragePoliciesHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetStoragePoliciesHeaders build() {
      return new GetStoragePoliciesHeaders(this);
    }
  }
}
