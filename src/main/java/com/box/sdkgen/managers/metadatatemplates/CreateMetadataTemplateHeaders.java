package com.box.sdkgen.managers.metadatatemplates;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateMetadataTemplateHeaders {

  public Map<String, String> extraHeaders;

  public CreateMetadataTemplateHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateMetadataTemplateHeaders(CreateMetadataTemplateHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateMetadataTemplateHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateMetadataTemplateHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateMetadataTemplateHeaders build() {
      return new CreateMetadataTemplateHeaders(this);
    }
  }
}
