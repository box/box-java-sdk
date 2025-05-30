package com.box.sdkgen.managers.fileversions;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateFileVersionByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateFileVersionByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateFileVersionByIdHeaders(UpdateFileVersionByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateFileVersionByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateFileVersionByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateFileVersionByIdHeaders build() {
      return new UpdateFileVersionByIdHeaders(this);
    }
  }
}
