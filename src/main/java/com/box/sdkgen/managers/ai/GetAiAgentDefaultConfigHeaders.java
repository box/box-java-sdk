package com.box.sdkgen.managers.ai;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetAiAgentDefaultConfigHeaders {

  public Map<String, String> extraHeaders;

  public GetAiAgentDefaultConfigHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetAiAgentDefaultConfigHeaders(GetAiAgentDefaultConfigHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetAiAgentDefaultConfigHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetAiAgentDefaultConfigHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetAiAgentDefaultConfigHeaders build() {
      return new GetAiAgentDefaultConfigHeaders(this);
    }
  }
}
