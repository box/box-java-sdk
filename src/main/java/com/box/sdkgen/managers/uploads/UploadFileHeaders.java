package com.box.sdkgen.managers.uploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UploadFileHeaders {

  public String contentMd5;

  public Map<String, String> extraHeaders;

  public UploadFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UploadFileHeaders(UploadFileHeadersBuilder builder) {
    this.contentMd5 = builder.contentMd5;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getContentMd5() {
    return contentMd5;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UploadFileHeadersBuilder {

    protected String contentMd5;

    protected Map<String, String> extraHeaders;

    public UploadFileHeadersBuilder contentMd5(String contentMd5) {
      this.contentMd5 = contentMd5;
      return this;
    }

    public UploadFileHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UploadFileHeaders build() {
      return new UploadFileHeaders(this);
    }
  }
}
