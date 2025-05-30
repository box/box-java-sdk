package com.box.sdkgen.managers.aistudio;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetAiAgentByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetAiAgentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetAiAgentByIdHeaders(GetAiAgentByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetAiAgentByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetAiAgentByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetAiAgentByIdHeaders build() {
      return new GetAiAgentByIdHeaders(this);
    }
  }
}
