package com.box.sdkgen.managers.uploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UploadFileVersionHeaders {

  public String ifMatch;

  public String contentMd5;

  public Map<String, String> extraHeaders;

  public UploadFileVersionHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UploadFileVersionHeaders(UploadFileVersionHeadersBuilder builder) {
    this.ifMatch = builder.ifMatch;
    this.contentMd5 = builder.contentMd5;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getIfMatch() {
    return ifMatch;
  }

  public String getContentMd5() {
    return contentMd5;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UploadFileVersionHeadersBuilder {

    protected String ifMatch;

    protected String contentMd5;

    protected Map<String, String> extraHeaders;

    public UploadFileVersionHeadersBuilder ifMatch(String ifMatch) {
      this.ifMatch = ifMatch;
      return this;
    }

    public UploadFileVersionHeadersBuilder contentMd5(String contentMd5) {
      this.contentMd5 = contentMd5;
      return this;
    }

    public UploadFileVersionHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UploadFileVersionHeaders build() {
      return new UploadFileVersionHeaders(this);
    }
  }
}
