package com.box.sdkgen.managers.usercollaborations;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateCollaborationByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateCollaborationByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateCollaborationByIdHeaders(UpdateCollaborationByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateCollaborationByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateCollaborationByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateCollaborationByIdHeaders build() {
      return new UpdateCollaborationByIdHeaders(this);
    }
  }
}
