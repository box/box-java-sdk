package com.box.sdkgen.managers.classifications;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateClassificationHeaders {

  public Map<String, String> extraHeaders;

  public UpdateClassificationHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateClassificationHeaders(UpdateClassificationHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateClassificationHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateClassificationHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateClassificationHeaders build() {
      return new UpdateClassificationHeaders(this);
    }
  }
}
