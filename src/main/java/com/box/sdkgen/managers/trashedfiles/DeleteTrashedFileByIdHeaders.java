package com.box.sdkgen.managers.trashedfiles;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteTrashedFileByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteTrashedFileByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteTrashedFileByIdHeaders(Builder builder) {
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

    public DeleteTrashedFileByIdHeaders build() {
      return new DeleteTrashedFileByIdHeaders(this);
    }
  }
}
