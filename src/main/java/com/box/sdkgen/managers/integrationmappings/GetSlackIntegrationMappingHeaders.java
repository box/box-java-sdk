package com.box.sdkgen.managers.integrationmappings;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetSlackIntegrationMappingHeaders {

  public Map<String, String> extraHeaders;

  public GetSlackIntegrationMappingHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetSlackIntegrationMappingHeaders(GetSlackIntegrationMappingHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetSlackIntegrationMappingHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetSlackIntegrationMappingHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetSlackIntegrationMappingHeaders build() {
      return new GetSlackIntegrationMappingHeaders(this);
    }
  }
}
