package com.box.sdkgen.managers.integrationmappings;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteSlackIntegrationMappingByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteSlackIntegrationMappingByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteSlackIntegrationMappingByIdHeaders(
      DeleteSlackIntegrationMappingByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteSlackIntegrationMappingByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteSlackIntegrationMappingByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteSlackIntegrationMappingByIdHeaders build() {
      return new DeleteSlackIntegrationMappingByIdHeaders(this);
    }
  }
}
