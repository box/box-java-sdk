package com.box.sdkgen.managers.signtemplates;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetSignTemplateByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetSignTemplateByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetSignTemplateByIdHeaders(Builder builder) {
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

    public GetSignTemplateByIdHeaders build() {
      return new GetSignTemplateByIdHeaders(this);
    }
  }
}
