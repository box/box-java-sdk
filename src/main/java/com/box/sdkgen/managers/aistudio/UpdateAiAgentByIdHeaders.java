package com.box.sdkgen.managers.aistudio;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateAiAgentByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateAiAgentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateAiAgentByIdHeaders(UpdateAiAgentByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateAiAgentByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateAiAgentByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateAiAgentByIdHeaders build() {
      return new UpdateAiAgentByIdHeaders(this);
    }
  }
}
