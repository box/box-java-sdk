package com.box.sdkgen.managers.sharedlinksweblinks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class RemoveSharedLinkFromWebLinkHeaders {

  public Map<String, String> extraHeaders;

  public RemoveSharedLinkFromWebLinkHeaders() {
    this.extraHeaders = mapOf();
  }

  protected RemoveSharedLinkFromWebLinkHeaders(RemoveSharedLinkFromWebLinkHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class RemoveSharedLinkFromWebLinkHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public RemoveSharedLinkFromWebLinkHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public RemoveSharedLinkFromWebLinkHeaders build() {
      return new RemoveSharedLinkFromWebLinkHeaders(this);
    }
  }
}
