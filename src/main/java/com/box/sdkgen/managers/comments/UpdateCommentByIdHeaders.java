package com.box.sdkgen.managers.comments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateCommentByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateCommentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateCommentByIdHeaders(Builder builder) {
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

    public UpdateCommentByIdHeaders build() {
      return new UpdateCommentByIdHeaders(this);
    }
  }
}
