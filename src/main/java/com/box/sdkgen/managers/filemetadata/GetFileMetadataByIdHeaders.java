package com.box.sdkgen.managers.filemetadata;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileMetadataByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetFileMetadataByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileMetadataByIdHeaders(Builder builder) {
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

    public GetFileMetadataByIdHeaders build() {
      return new GetFileMetadataByIdHeaders(this);
    }
  }
}
