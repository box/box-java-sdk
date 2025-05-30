package com.box.sdkgen.managers.filemetadata;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteFileMetadataByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteFileMetadataByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteFileMetadataByIdHeaders(DeleteFileMetadataByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteFileMetadataByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteFileMetadataByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteFileMetadataByIdHeaders build() {
      return new DeleteFileMetadataByIdHeaders(this);
    }
  }
}
