package com.box.sdkgen.managers.trashedfolders;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class RestoreFolderFromTrashHeaders {

  public Map<String, String> extraHeaders;

  public RestoreFolderFromTrashHeaders() {
    this.extraHeaders = mapOf();
  }

  protected RestoreFolderFromTrashHeaders(Builder builder) {
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

    public RestoreFolderFromTrashHeaders build() {
      return new RestoreFolderFromTrashHeaders(this);
    }
  }
}
