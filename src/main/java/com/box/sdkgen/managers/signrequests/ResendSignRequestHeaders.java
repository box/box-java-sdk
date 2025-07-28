package com.box.sdkgen.managers.signrequests;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class ResendSignRequestHeaders {

  public Map<String, String> extraHeaders;

  public ResendSignRequestHeaders() {
    this.extraHeaders = mapOf();
  }

  protected ResendSignRequestHeaders(Builder builder) {
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

    public ResendSignRequestHeaders build() {
      return new ResendSignRequestHeaders(this);
    }
  }
}
