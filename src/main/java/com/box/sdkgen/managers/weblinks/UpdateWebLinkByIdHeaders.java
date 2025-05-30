package com.box.sdkgen.managers.weblinks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateWebLinkByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateWebLinkByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateWebLinkByIdHeaders(UpdateWebLinkByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateWebLinkByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateWebLinkByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateWebLinkByIdHeaders build() {
      return new UpdateWebLinkByIdHeaders(this);
    }
  }
}
