package com.box.sdkgen.managers.collaborationallowlistentries;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteCollaborationWhitelistEntryByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteCollaborationWhitelistEntryByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteCollaborationWhitelistEntryByIdHeaders(
      DeleteCollaborationWhitelistEntryByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteCollaborationWhitelistEntryByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteCollaborationWhitelistEntryByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteCollaborationWhitelistEntryByIdHeaders build() {
      return new DeleteCollaborationWhitelistEntryByIdHeaders(this);
    }
  }
}
