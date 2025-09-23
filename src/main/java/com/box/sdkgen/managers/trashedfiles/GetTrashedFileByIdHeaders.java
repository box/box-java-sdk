package com.box.sdkgen.managers.trashedfiles;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetTrashedFileByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetTrashedFileByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetTrashedFileByIdHeaders(Builder builder) {
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

    public GetTrashedFileByIdHeaders build() {
      return new GetTrashedFileByIdHeaders(this);
    }
  }
}
