package com.box.sdkgen.managers.sharedlinksfiles;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateSharedLinkOnFileHeaders {

  public Map<String, String> extraHeaders;

  public UpdateSharedLinkOnFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateSharedLinkOnFileHeaders(UpdateSharedLinkOnFileHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateSharedLinkOnFileHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateSharedLinkOnFileHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateSharedLinkOnFileHeaders build() {
      return new UpdateSharedLinkOnFileHeaders(this);
    }
  }
}
