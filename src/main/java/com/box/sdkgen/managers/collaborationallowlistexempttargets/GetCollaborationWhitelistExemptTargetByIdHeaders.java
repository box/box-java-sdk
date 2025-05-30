package com.box.sdkgen.managers.collaborationallowlistexempttargets;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetCollaborationWhitelistExemptTargetByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetCollaborationWhitelistExemptTargetByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetCollaborationWhitelistExemptTargetByIdHeaders(
      GetCollaborationWhitelistExemptTargetByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetCollaborationWhitelistExemptTargetByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetCollaborationWhitelistExemptTargetByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetCollaborationWhitelistExemptTargetByIdHeaders build() {
      return new GetCollaborationWhitelistExemptTargetByIdHeaders(this);
    }
  }
}
