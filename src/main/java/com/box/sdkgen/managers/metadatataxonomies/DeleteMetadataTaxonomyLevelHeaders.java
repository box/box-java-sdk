package com.box.sdkgen.managers.metadatataxonomies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteMetadataTaxonomyLevelHeaders {

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public DeleteMetadataTaxonomyLevelHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteMetadataTaxonomyLevelHeaders(Builder builder) {
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

    public DeleteMetadataTaxonomyLevelHeaders build() {
      return new DeleteMetadataTaxonomyLevelHeaders(this);
    }
  }
}
