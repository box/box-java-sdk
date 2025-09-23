package com.box.sdkgen.managers.collaborationallowlistexempttargets;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateCollaborationWhitelistExemptTargetHeaders {

  public Map<String, String> extraHeaders;

  public CreateCollaborationWhitelistExemptTargetHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateCollaborationWhitelistExemptTargetHeaders(Builder builder) {
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

    public CreateCollaborationWhitelistExemptTargetHeaders build() {
      return new CreateCollaborationWhitelistExemptTargetHeaders(this);
    }
  }
}
