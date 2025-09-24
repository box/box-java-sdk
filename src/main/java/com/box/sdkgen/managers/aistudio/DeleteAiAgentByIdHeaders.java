package com.box.sdkgen.managers.aistudio;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteAiAgentByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteAiAgentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteAiAgentByIdHeaders(Builder builder) {
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

    public DeleteAiAgentByIdHeaders build() {
      return new DeleteAiAgentByIdHeaders(this);
    }
  }
}
