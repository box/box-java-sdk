package com.box.sdkgen.managers.usercollaborations;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteCollaborationByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteCollaborationByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteCollaborationByIdHeaders(DeleteCollaborationByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteCollaborationByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteCollaborationByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteCollaborationByIdHeaders build() {
      return new DeleteCollaborationByIdHeaders(this);
    }
  }
}
