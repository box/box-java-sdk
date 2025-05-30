package com.box.sdkgen.managers.groups;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateGroupByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateGroupByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateGroupByIdHeaders(UpdateGroupByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateGroupByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateGroupByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateGroupByIdHeaders build() {
      return new UpdateGroupByIdHeaders(this);
    }
  }
}
