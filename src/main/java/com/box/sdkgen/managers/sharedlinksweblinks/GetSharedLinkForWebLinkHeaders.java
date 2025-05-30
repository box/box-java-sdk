package com.box.sdkgen.managers.sharedlinksweblinks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetSharedLinkForWebLinkHeaders {

  public Map<String, String> extraHeaders;

  public GetSharedLinkForWebLinkHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetSharedLinkForWebLinkHeaders(GetSharedLinkForWebLinkHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetSharedLinkForWebLinkHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetSharedLinkForWebLinkHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetSharedLinkForWebLinkHeaders build() {
      return new GetSharedLinkForWebLinkHeaders(this);
    }
  }
}
