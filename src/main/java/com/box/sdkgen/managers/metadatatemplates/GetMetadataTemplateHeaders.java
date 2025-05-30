package com.box.sdkgen.managers.metadatatemplates;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetMetadataTemplateHeaders {

  public Map<String, String> extraHeaders;

  public GetMetadataTemplateHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetMetadataTemplateHeaders(GetMetadataTemplateHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetMetadataTemplateHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetMetadataTemplateHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetMetadataTemplateHeaders build() {
      return new GetMetadataTemplateHeaders(this);
    }
  }
}
