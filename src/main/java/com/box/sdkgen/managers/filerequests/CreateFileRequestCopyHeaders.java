package com.box.sdkgen.managers.filerequests;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateFileRequestCopyHeaders {

  public Map<String, String> extraHeaders;

  public CreateFileRequestCopyHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateFileRequestCopyHeaders(CreateFileRequestCopyHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateFileRequestCopyHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateFileRequestCopyHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateFileRequestCopyHeaders build() {
      return new CreateFileRequestCopyHeaders(this);
    }
  }
}
