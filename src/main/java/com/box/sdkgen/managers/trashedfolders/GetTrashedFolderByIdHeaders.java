package com.box.sdkgen.managers.trashedfolders;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetTrashedFolderByIdHeaders {

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public GetTrashedFolderByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetTrashedFolderByIdHeaders(Builder builder) {
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

    public GetTrashedFolderByIdHeaders build() {
      if (this.extraHeaders == null) {
        this.extraHeaders = mapOf();
      }
      return new GetTrashedFolderByIdHeaders(this);
    }
  }
}
