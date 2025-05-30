package com.box.sdkgen.managers.classifications;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetClassificationTemplateHeaders {

  public Map<String, String> extraHeaders;

  public GetClassificationTemplateHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetClassificationTemplateHeaders(GetClassificationTemplateHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetClassificationTemplateHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetClassificationTemplateHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetClassificationTemplateHeaders build() {
      return new GetClassificationTemplateHeaders(this);
    }
  }
}
