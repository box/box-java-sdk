package com.box.sdkgen.managers.aistudio;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetAiAgentsHeaders {

  public Map<String, String> extraHeaders;

  public GetAiAgentsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetAiAgentsHeaders(GetAiAgentsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetAiAgentsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetAiAgentsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetAiAgentsHeaders build() {
      return new GetAiAgentsHeaders(this);
    }
  }
}
