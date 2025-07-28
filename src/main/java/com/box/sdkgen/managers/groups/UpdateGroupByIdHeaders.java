package com.box.sdkgen.managers.groups;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateGroupByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateGroupByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateGroupByIdHeaders(Builder builder) {
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

    public UpdateGroupByIdHeaders build() {
      return new UpdateGroupByIdHeaders(this);
    }
  }
}
