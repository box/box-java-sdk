package com.box.sdkgen.managers.trashedfolders;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class RestoreFolderFromTrashHeaders {

  public Map<String, String> extraHeaders;

  public RestoreFolderFromTrashHeaders() {
    this.extraHeaders = mapOf();
  }

  protected RestoreFolderFromTrashHeaders(RestoreFolderFromTrashHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class RestoreFolderFromTrashHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public RestoreFolderFromTrashHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public RestoreFolderFromTrashHeaders build() {
      return new RestoreFolderFromTrashHeaders(this);
    }
  }
}
