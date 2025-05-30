package com.box.sdkgen.managers.listcollaborations;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetCollaborationsHeaders {

  public Map<String, String> extraHeaders;

  public GetCollaborationsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetCollaborationsHeaders(GetCollaborationsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetCollaborationsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetCollaborationsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetCollaborationsHeaders build() {
      return new GetCollaborationsHeaders(this);
    }
  }
}
