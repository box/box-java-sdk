package com.box.sdkgen.managers.integrationmappings;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateTeamsIntegrationMappingHeaders {

  public Map<String, String> extraHeaders;

  public CreateTeamsIntegrationMappingHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateTeamsIntegrationMappingHeaders(
      CreateTeamsIntegrationMappingHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateTeamsIntegrationMappingHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateTeamsIntegrationMappingHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateTeamsIntegrationMappingHeaders build() {
      return new CreateTeamsIntegrationMappingHeaders(this);
    }
  }
}
