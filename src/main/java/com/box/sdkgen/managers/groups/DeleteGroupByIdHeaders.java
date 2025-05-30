package com.box.sdkgen.managers.groups;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteGroupByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteGroupByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteGroupByIdHeaders(DeleteGroupByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteGroupByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteGroupByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteGroupByIdHeaders build() {
      return new DeleteGroupByIdHeaders(this);
    }
  }
}
