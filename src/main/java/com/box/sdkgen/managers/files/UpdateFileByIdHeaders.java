package com.box.sdkgen.managers.files;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateFileByIdHeaders {

  public String ifMatch;

  public Map<String, String> extraHeaders;

  public UpdateFileByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateFileByIdHeaders(UpdateFileByIdHeadersBuilder builder) {
    this.ifMatch = builder.ifMatch;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getIfMatch() {
    return ifMatch;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateFileByIdHeadersBuilder {

    protected String ifMatch;

    protected Map<String, String> extraHeaders;

    public UpdateFileByIdHeadersBuilder ifMatch(String ifMatch) {
      this.ifMatch = ifMatch;
      return this;
    }

    public UpdateFileByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateFileByIdHeaders build() {
      return new UpdateFileByIdHeaders(this);
    }
  }
}
