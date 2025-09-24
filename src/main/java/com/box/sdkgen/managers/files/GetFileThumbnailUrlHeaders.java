package com.box.sdkgen.managers.files;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileThumbnailUrlHeaders {

  public Map<String, String> extraHeaders;

  public GetFileThumbnailUrlHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileThumbnailUrlHeaders(Builder builder) {
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

    public GetFileThumbnailUrlHeaders build() {
      return new GetFileThumbnailUrlHeaders(this);
    }
  }
}
