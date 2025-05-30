package com.box.sdkgen.managers.usercollaborations;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetCollaborationByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetCollaborationByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetCollaborationByIdHeaders(GetCollaborationByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetCollaborationByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetCollaborationByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetCollaborationByIdHeaders build() {
      return new GetCollaborationByIdHeaders(this);
    }
  }
}
