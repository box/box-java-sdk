package com.box.sdkgen.managers.sharedlinksweblinks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateSharedLinkOnWebLinkHeaders {

  public Map<String, String> extraHeaders;

  public UpdateSharedLinkOnWebLinkHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateSharedLinkOnWebLinkHeaders(UpdateSharedLinkOnWebLinkHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateSharedLinkOnWebLinkHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateSharedLinkOnWebLinkHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateSharedLinkOnWebLinkHeaders build() {
      return new UpdateSharedLinkOnWebLinkHeaders(this);
    }
  }
}
