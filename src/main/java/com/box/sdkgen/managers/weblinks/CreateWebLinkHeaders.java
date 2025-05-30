package com.box.sdkgen.managers.weblinks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateWebLinkHeaders {

  public Map<String, String> extraHeaders;

  public CreateWebLinkHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateWebLinkHeaders(CreateWebLinkHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateWebLinkHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateWebLinkHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateWebLinkHeaders build() {
      return new CreateWebLinkHeaders(this);
    }
  }
}
