package com.box.sdkgen.managers.collaborationallowlistexempttargets;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetCollaborationWhitelistExemptTargetByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetCollaborationWhitelistExemptTargetByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetCollaborationWhitelistExemptTargetByIdHeaders(Builder builder) {
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

    public GetCollaborationWhitelistExemptTargetByIdHeaders build() {
      return new GetCollaborationWhitelistExemptTargetByIdHeaders(this);
    }
  }
}
