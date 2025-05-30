package com.box.sdkgen.managers.metadatatemplates;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetGlobalMetadataTemplatesHeaders {

  public Map<String, String> extraHeaders;

  public GetGlobalMetadataTemplatesHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetGlobalMetadataTemplatesHeaders(GetGlobalMetadataTemplatesHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetGlobalMetadataTemplatesHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetGlobalMetadataTemplatesHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetGlobalMetadataTemplatesHeaders build() {
      return new GetGlobalMetadataTemplatesHeaders(this);
    }
  }
}
