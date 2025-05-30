package com.box.sdkgen.managers.metadatatemplates;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteMetadataTemplateHeaders {

  public Map<String, String> extraHeaders;

  public DeleteMetadataTemplateHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteMetadataTemplateHeaders(DeleteMetadataTemplateHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteMetadataTemplateHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteMetadataTemplateHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteMetadataTemplateHeaders build() {
      return new DeleteMetadataTemplateHeaders(this);
    }
  }
}
