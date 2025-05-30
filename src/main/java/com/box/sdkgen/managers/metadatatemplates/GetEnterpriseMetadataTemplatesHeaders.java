package com.box.sdkgen.managers.metadatatemplates;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetEnterpriseMetadataTemplatesHeaders {

  public Map<String, String> extraHeaders;

  public GetEnterpriseMetadataTemplatesHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetEnterpriseMetadataTemplatesHeaders(
      GetEnterpriseMetadataTemplatesHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetEnterpriseMetadataTemplatesHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetEnterpriseMetadataTemplatesHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetEnterpriseMetadataTemplatesHeaders build() {
      return new GetEnterpriseMetadataTemplatesHeaders(this);
    }
  }
}
