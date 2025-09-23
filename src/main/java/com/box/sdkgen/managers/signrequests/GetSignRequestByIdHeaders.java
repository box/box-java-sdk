package com.box.sdkgen.managers.signrequests;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetSignRequestByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetSignRequestByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetSignRequestByIdHeaders(Builder builder) {
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

    public GetSignRequestByIdHeaders build() {
      return new GetSignRequestByIdHeaders(this);
    }
  }
}
