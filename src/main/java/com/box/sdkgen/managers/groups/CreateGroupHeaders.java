package com.box.sdkgen.managers.groups;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateGroupHeaders {

  public Map<String, String> extraHeaders;

  public CreateGroupHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateGroupHeaders(CreateGroupHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateGroupHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateGroupHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateGroupHeaders build() {
      return new CreateGroupHeaders(this);
    }
  }
}
