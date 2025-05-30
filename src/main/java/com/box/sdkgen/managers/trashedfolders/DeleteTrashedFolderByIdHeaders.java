package com.box.sdkgen.managers.trashedfolders;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteTrashedFolderByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteTrashedFolderByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteTrashedFolderByIdHeaders(DeleteTrashedFolderByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteTrashedFolderByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteTrashedFolderByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteTrashedFolderByIdHeaders build() {
      return new DeleteTrashedFolderByIdHeaders(this);
    }
  }
}
