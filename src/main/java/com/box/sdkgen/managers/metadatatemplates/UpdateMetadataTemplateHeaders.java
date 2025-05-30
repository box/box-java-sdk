package com.box.sdkgen.managers.metadatatemplates;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateMetadataTemplateHeaders {

  public Map<String, String> extraHeaders;

  public UpdateMetadataTemplateHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateMetadataTemplateHeaders(UpdateMetadataTemplateHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateMetadataTemplateHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateMetadataTemplateHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateMetadataTemplateHeaders build() {
      return new UpdateMetadataTemplateHeaders(this);
    }
  }
}
