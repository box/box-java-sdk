package com.box.sdkgen.managers.collaborationallowlistentries;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetCollaborationWhitelistEntriesHeaders {

  public Map<String, String> extraHeaders;

  public GetCollaborationWhitelistEntriesHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetCollaborationWhitelistEntriesHeaders(
      GetCollaborationWhitelistEntriesHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetCollaborationWhitelistEntriesHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetCollaborationWhitelistEntriesHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetCollaborationWhitelistEntriesHeaders build() {
      return new GetCollaborationWhitelistEntriesHeaders(this);
    }
  }
}
