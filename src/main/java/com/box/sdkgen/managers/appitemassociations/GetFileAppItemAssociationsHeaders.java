package com.box.sdkgen.managers.appitemassociations;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileAppItemAssociationsHeaders {

  public Map<String, String> extraHeaders;

  public GetFileAppItemAssociationsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileAppItemAssociationsHeaders(Builder builder) {
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

    public GetFileAppItemAssociationsHeaders build() {
      return new GetFileAppItemAssociationsHeaders(this);
    }
  }
}
