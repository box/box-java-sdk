package com.box.sdkgen.managers.comments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileCommentsHeaders {

  public Map<String, String> extraHeaders;

  public GetFileCommentsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileCommentsHeaders(GetFileCommentsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFileCommentsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFileCommentsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileCommentsHeaders build() {
      return new GetFileCommentsHeaders(this);
    }
  }
}
