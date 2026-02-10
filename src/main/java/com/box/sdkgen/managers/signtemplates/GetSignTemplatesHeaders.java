package com.box.sdkgen.managers.signtemplates;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetSignTemplatesHeaders {

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public GetSignTemplatesHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetSignTemplatesHeaders(Builder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected Map<String, String> extraHeaders;

    public Builder() {}

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetSignTemplatesHeaders build() {
      if (this.extraHeaders == null) {
        this.extraHeaders = mapOf();
      }
      return new GetSignTemplatesHeaders(this);
    }
  }
}
