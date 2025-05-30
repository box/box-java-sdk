package com.box.sdkgen.managers.metadatatemplates;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetMetadataTemplatesByInstanceIdHeaders {

  public Map<String, String> extraHeaders;

  public GetMetadataTemplatesByInstanceIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetMetadataTemplatesByInstanceIdHeaders(
      GetMetadataTemplatesByInstanceIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetMetadataTemplatesByInstanceIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetMetadataTemplatesByInstanceIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetMetadataTemplatesByInstanceIdHeaders build() {
      return new GetMetadataTemplatesByInstanceIdHeaders(this);
    }
  }
}
