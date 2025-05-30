package com.box.sdkgen.managers.trashedweblinks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteTrashedWebLinkByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteTrashedWebLinkByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteTrashedWebLinkByIdHeaders(DeleteTrashedWebLinkByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteTrashedWebLinkByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteTrashedWebLinkByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteTrashedWebLinkByIdHeaders build() {
      return new DeleteTrashedWebLinkByIdHeaders(this);
    }
  }
}
