package com.box.sdkgen.managers.foldermetadata;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFolderMetadataHeaders {

  public Map<String, String> extraHeaders;

  public GetFolderMetadataHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFolderMetadataHeaders(GetFolderMetadataHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFolderMetadataHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFolderMetadataHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFolderMetadataHeaders build() {
      return new GetFolderMetadataHeaders(this);
    }
  }
}
