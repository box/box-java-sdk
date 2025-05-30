package com.box.sdkgen.managers.filemetadata;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileMetadataByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetFileMetadataByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileMetadataByIdHeaders(GetFileMetadataByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFileMetadataByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFileMetadataByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileMetadataByIdHeaders build() {
      return new GetFileMetadataByIdHeaders(this);
    }
  }
}
