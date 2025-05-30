package com.box.sdkgen.managers.foldermetadata;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteFolderMetadataByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteFolderMetadataByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteFolderMetadataByIdHeaders(DeleteFolderMetadataByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteFolderMetadataByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteFolderMetadataByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteFolderMetadataByIdHeaders build() {
      return new DeleteFolderMetadataByIdHeaders(this);
    }
  }
}
