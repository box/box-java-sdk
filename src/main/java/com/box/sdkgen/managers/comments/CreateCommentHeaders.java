package com.box.sdkgen.managers.comments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateCommentHeaders {

  public Map<String, String> extraHeaders;

  public CreateCommentHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateCommentHeaders(CreateCommentHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateCommentHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateCommentHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateCommentHeaders build() {
      return new CreateCommentHeaders(this);
    }
  }
}
