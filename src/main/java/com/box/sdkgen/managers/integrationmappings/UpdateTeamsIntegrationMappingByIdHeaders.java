package com.box.sdkgen.managers.integrationmappings;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateTeamsIntegrationMappingByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateTeamsIntegrationMappingByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateTeamsIntegrationMappingByIdHeaders(Builder builder) {
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

    public UpdateTeamsIntegrationMappingByIdHeaders build() {
      return new UpdateTeamsIntegrationMappingByIdHeaders(this);
    }
  }
}
