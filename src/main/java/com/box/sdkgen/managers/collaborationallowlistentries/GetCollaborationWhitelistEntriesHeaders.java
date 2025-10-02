package com.box.sdkgen.managers.collaborationallowlistentries;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetCollaborationWhitelistEntriesHeaders {

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public GetCollaborationWhitelistEntriesHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetCollaborationWhitelistEntriesHeaders(Builder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetCollaborationWhitelistEntriesHeaders build() {
      return new GetCollaborationWhitelistEntriesHeaders(this);
    }
  }
}
