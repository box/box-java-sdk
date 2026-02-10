package com.box.sdkgen.managers.search;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class SearchByMetadataQueryHeaders {

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public SearchByMetadataQueryHeaders() {
    this.extraHeaders = mapOf();
  }

  protected SearchByMetadataQueryHeaders(Builder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected Map<String, String> extraHeaders;

    public Builder() {}

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public SearchByMetadataQueryHeaders build() {
      if (this.extraHeaders == null) {
        this.extraHeaders = mapOf();
      }
      return new SearchByMetadataQueryHeaders(this);
    }
  }
}
