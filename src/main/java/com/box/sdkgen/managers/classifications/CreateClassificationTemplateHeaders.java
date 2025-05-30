package com.box.sdkgen.managers.classifications;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateClassificationTemplateHeaders {

  public Map<String, String> extraHeaders;

  public CreateClassificationTemplateHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateClassificationTemplateHeaders(
      CreateClassificationTemplateHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateClassificationTemplateHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateClassificationTemplateHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateClassificationTemplateHeaders build() {
      return new CreateClassificationTemplateHeaders(this);
    }
  }
}
