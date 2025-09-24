package com.box.sdkgen.managers.retentionpolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteRetentionPolicyByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteRetentionPolicyByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteRetentionPolicyByIdHeaders(Builder builder) {
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

    public DeleteRetentionPolicyByIdHeaders build() {
      return new DeleteRetentionPolicyByIdHeaders(this);
    }
  }
}
