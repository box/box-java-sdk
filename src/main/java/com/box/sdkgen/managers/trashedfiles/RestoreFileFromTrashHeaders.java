package com.box.sdkgen.managers.trashedfiles;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class RestoreFileFromTrashHeaders {

  public Map<String, String> extraHeaders;

  public RestoreFileFromTrashHeaders() {
    this.extraHeaders = mapOf();
  }

  protected RestoreFileFromTrashHeaders(RestoreFileFromTrashHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class RestoreFileFromTrashHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public RestoreFileFromTrashHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public RestoreFileFromTrashHeaders build() {
      return new RestoreFileFromTrashHeaders(this);
    }
  }
}
