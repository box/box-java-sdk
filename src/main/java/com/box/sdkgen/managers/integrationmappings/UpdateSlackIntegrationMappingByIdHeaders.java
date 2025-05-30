package com.box.sdkgen.managers.integrationmappings;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateSlackIntegrationMappingByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateSlackIntegrationMappingByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateSlackIntegrationMappingByIdHeaders(
      UpdateSlackIntegrationMappingByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateSlackIntegrationMappingByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateSlackIntegrationMappingByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateSlackIntegrationMappingByIdHeaders build() {
      return new UpdateSlackIntegrationMappingByIdHeaders(this);
    }
  }
}
