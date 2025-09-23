package com.box.sdkgen.managers.collaborationallowlistentries;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetCollaborationWhitelistEntryByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetCollaborationWhitelistEntryByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetCollaborationWhitelistEntryByIdHeaders(Builder builder) {
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

    public GetCollaborationWhitelistEntryByIdHeaders build() {
      return new GetCollaborationWhitelistEntryByIdHeaders(this);
    }
  }
}
