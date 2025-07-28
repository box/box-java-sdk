package com.box.sdkgen.managers.integrationmappings;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateSlackIntegrationMappingByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateSlackIntegrationMappingByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateSlackIntegrationMappingByIdHeaders(Builder builder) {
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

    public UpdateSlackIntegrationMappingByIdHeaders build() {
      return new UpdateSlackIntegrationMappingByIdHeaders(this);
    }
  }
}
