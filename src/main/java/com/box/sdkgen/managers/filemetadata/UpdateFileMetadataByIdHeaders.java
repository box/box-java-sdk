package com.box.sdkgen.managers.filemetadata;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateFileMetadataByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateFileMetadataByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateFileMetadataByIdHeaders(UpdateFileMetadataByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateFileMetadataByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateFileMetadataByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateFileMetadataByIdHeaders build() {
      return new UpdateFileMetadataByIdHeaders(this);
    }
  }
}
