package com.box.sdkgen.managers.classifications;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetClassificationTemplateHeaders {

  public Map<String, String> extraHeaders;

  public GetClassificationTemplateHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetClassificationTemplateHeaders(Builder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetClassificationTemplateHeaders build() {
      return new GetClassificationTemplateHeaders(this);
    }
  }
}
