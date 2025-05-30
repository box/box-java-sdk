package com.box.sdkgen.managers.collaborationallowlistexempttargets;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteCollaborationWhitelistExemptTargetByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteCollaborationWhitelistExemptTargetByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteCollaborationWhitelistExemptTargetByIdHeaders(
      DeleteCollaborationWhitelistExemptTargetByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteCollaborationWhitelistExemptTargetByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteCollaborationWhitelistExemptTargetByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteCollaborationWhitelistExemptTargetByIdHeaders build() {
      return new DeleteCollaborationWhitelistExemptTargetByIdHeaders(this);
    }
  }
}
