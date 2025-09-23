package com.box.sdkgen.managers.integrationmappings;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateSlackIntegrationMappingHeaders {

  public Map<String, String> extraHeaders;

  public CreateSlackIntegrationMappingHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateSlackIntegrationMappingHeaders(Builder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateSlackIntegrationMappingHeaders build() {
      return new CreateSlackIntegrationMappingHeaders(this);
    }
  }
}
