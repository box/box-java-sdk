package com.box.sdkgen.managers.signtemplates;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetSignTemplatesHeaders {

  public Map<String, String> extraHeaders;

  public GetSignTemplatesHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetSignTemplatesHeaders(GetSignTemplatesHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetSignTemplatesHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetSignTemplatesHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetSignTemplatesHeaders build() {
      return new GetSignTemplatesHeaders(this);
    }
  }
}
