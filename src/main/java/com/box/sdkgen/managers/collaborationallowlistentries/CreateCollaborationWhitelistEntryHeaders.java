package com.box.sdkgen.managers.collaborationallowlistentries;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateCollaborationWhitelistEntryHeaders {

  public Map<String, String> extraHeaders;

  public CreateCollaborationWhitelistEntryHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateCollaborationWhitelistEntryHeaders(Builder builder) {
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

    public CreateCollaborationWhitelistEntryHeaders build() {
      return new CreateCollaborationWhitelistEntryHeaders(this);
    }
  }
}
