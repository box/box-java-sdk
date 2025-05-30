package com.box.sdkgen.managers.users;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateUserByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateUserByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateUserByIdHeaders(UpdateUserByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateUserByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateUserByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateUserByIdHeaders build() {
      return new UpdateUserByIdHeaders(this);
    }
  }
}
