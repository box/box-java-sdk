package com.box.sdkgen.managers.aistudio;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteAiAgentByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteAiAgentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteAiAgentByIdHeaders(DeleteAiAgentByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteAiAgentByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteAiAgentByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteAiAgentByIdHeaders build() {
      return new DeleteAiAgentByIdHeaders(this);
    }
  }
}
