package com.box.sdkgen.managers.integrationmappings;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetTeamsIntegrationMappingHeaders {

  public Map<String, String> extraHeaders;

  public GetTeamsIntegrationMappingHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetTeamsIntegrationMappingHeaders(GetTeamsIntegrationMappingHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetTeamsIntegrationMappingHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetTeamsIntegrationMappingHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetTeamsIntegrationMappingHeaders build() {
      return new GetTeamsIntegrationMappingHeaders(this);
    }
  }
}
