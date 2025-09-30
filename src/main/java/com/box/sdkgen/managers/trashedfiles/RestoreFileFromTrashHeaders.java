package com.box.sdkgen.managers.trashedfiles;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class RestoreFileFromTrashHeaders {

  public Map<String, String> extraHeaders;

  public RestoreFileFromTrashHeaders() {
    this.extraHeaders = mapOf();
  }

  protected RestoreFileFromTrashHeaders(Builder builder) {
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

    public RestoreFileFromTrashHeaders build() {
      return new RestoreFileFromTrashHeaders(this);
    }
  }
}
