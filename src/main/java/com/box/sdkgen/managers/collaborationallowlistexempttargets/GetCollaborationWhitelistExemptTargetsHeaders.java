package com.box.sdkgen.managers.collaborationallowlistexempttargets;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetCollaborationWhitelistExemptTargetsHeaders {

  public Map<String, String> extraHeaders;

  public GetCollaborationWhitelistExemptTargetsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetCollaborationWhitelistExemptTargetsHeaders(
      GetCollaborationWhitelistExemptTargetsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetCollaborationWhitelistExemptTargetsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetCollaborationWhitelistExemptTargetsHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetCollaborationWhitelistExemptTargetsHeaders build() {
      return new GetCollaborationWhitelistExemptTargetsHeaders(this);
    }
  }
}
