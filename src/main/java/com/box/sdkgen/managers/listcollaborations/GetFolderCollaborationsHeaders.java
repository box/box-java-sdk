package com.box.sdkgen.managers.listcollaborations;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFolderCollaborationsHeaders {

  public Map<String, String> extraHeaders;

  public GetFolderCollaborationsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFolderCollaborationsHeaders(GetFolderCollaborationsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFolderCollaborationsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFolderCollaborationsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFolderCollaborationsHeaders build() {
      return new GetFolderCollaborationsHeaders(this);
    }
  }
}
