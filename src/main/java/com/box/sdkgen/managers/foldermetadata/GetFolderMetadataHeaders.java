package com.box.sdkgen.managers.foldermetadata;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFolderMetadataHeaders {

  public Map<String, String> extraHeaders;

  public GetFolderMetadataHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFolderMetadataHeaders(Builder builder) {
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

    public GetFolderMetadataHeaders build() {
      return new GetFolderMetadataHeaders(this);
    }
  }
}
