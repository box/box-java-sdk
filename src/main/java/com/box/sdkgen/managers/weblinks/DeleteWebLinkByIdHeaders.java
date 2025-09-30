package com.box.sdkgen.managers.weblinks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteWebLinkByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteWebLinkByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteWebLinkByIdHeaders(Builder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteWebLinkByIdHeaders build() {
      return new DeleteWebLinkByIdHeaders(this);
    }
  }
}
