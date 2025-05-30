package com.box.sdkgen.managers.foldermetadata;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateFolderMetadataByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateFolderMetadataByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateFolderMetadataByIdHeaders(UpdateFolderMetadataByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateFolderMetadataByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateFolderMetadataByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateFolderMetadataByIdHeaders build() {
      return new UpdateFolderMetadataByIdHeaders(this);
    }
  }
}
