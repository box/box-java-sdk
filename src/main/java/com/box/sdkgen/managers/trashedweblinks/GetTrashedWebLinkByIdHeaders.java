package com.box.sdkgen.managers.trashedweblinks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetTrashedWebLinkByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetTrashedWebLinkByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetTrashedWebLinkByIdHeaders(GetTrashedWebLinkByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetTrashedWebLinkByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetTrashedWebLinkByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetTrashedWebLinkByIdHeaders build() {
      return new GetTrashedWebLinkByIdHeaders(this);
    }
  }
}
