package com.box.sdkgen.managers.listcollaborations;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileCollaborationsHeaders {

  public Map<String, String> extraHeaders;

  public GetFileCollaborationsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileCollaborationsHeaders(GetFileCollaborationsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFileCollaborationsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFileCollaborationsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileCollaborationsHeaders build() {
      return new GetFileCollaborationsHeaders(this);
    }
  }
}
