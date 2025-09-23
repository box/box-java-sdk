package com.box.sdkgen.managers.weblinks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateWebLinkByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateWebLinkByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateWebLinkByIdHeaders(Builder builder) {
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

    public UpdateWebLinkByIdHeaders build() {
      return new UpdateWebLinkByIdHeaders(this);
    }
  }
}
