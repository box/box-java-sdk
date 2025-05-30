package com.box.sdkgen.managers.metadatatemplates;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetMetadataTemplateByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetMetadataTemplateByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetMetadataTemplateByIdHeaders(GetMetadataTemplateByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetMetadataTemplateByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetMetadataTemplateByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetMetadataTemplateByIdHeaders build() {
      return new GetMetadataTemplateByIdHeaders(this);
    }
  }
}
