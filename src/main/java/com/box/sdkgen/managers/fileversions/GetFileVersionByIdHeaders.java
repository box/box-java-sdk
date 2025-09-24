package com.box.sdkgen.managers.fileversions;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileVersionByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetFileVersionByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileVersionByIdHeaders(Builder builder) {
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

    public GetFileVersionByIdHeaders build() {
      return new GetFileVersionByIdHeaders(this);
    }
  }
}
