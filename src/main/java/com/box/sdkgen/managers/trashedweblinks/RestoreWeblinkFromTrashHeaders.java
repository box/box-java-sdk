package com.box.sdkgen.managers.trashedweblinks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class RestoreWeblinkFromTrashHeaders {

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public RestoreWeblinkFromTrashHeaders() {
    this.extraHeaders = mapOf();
  }

  protected RestoreWeblinkFromTrashHeaders(Builder builder) {
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

    public RestoreWeblinkFromTrashHeaders build() {
      return new RestoreWeblinkFromTrashHeaders(this);
    }
  }
}
